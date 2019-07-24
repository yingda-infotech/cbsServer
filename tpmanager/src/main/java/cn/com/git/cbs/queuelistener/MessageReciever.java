package cn.com.git.cbs.queuelistener;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.cbs.exception.CbsRunTimeException;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.ExceptionUtils;
import cn.com.git.cbs.platform.utils.JSONUtils;
import cn.com.git.cbs.platform.utils.mq.SendQueueUtil;
import cn.com.git.cbs.tpmanager.TranMain;

/**
 * 
 * @author DengJia 消息接收，从in队列获取消息，启动TranMain流程，处理完毕通过out队列发送消息
 */
//@Profile({"asyncExecutor","asyncServer"})
//@Component
//@Qualifier("messageReciever")
public class MessageReciever implements MessageListener, InitializingBean {

	private final static PlatformLogger LOGGER = PlatformLogger.create();
	private final static String ERR_RSP_CODE = "PL9999";
	
	/**
	 * 使用Map作为路由配置
	 */
	private Map<String, String> outQueueMap;

//	@Value("#{'${GWQ.nodeList}'.split(',')}")
	private String nodeList;
//	@Value("#{'${GWQ.outQueueList}'.split(',')}")
	private String outQueueList;

	@Autowired
	private TranMain engine;

	@Override
	public void onMessage(Message inMessage) {
		DataObject request = null;
		if (inMessage instanceof TextMessage) {
			try {
				String clientID = inMessage.getStringProperty("clientID");
				String coreleationID = inMessage.getStringProperty("coreleationID");

				Map<String, String> options = new HashMap<String, String>();
				options.put("clientID", clientID);
				options.put("coreleationID", coreleationID);

				request = JSONUtils.string2DataObject(((TextMessage) inMessage).getText());
				DataObject response = null;
				// TODO:需要根据clientID找到对应outQueue
				String outQueueName = outQueueMap.get(clientID);
				try {
					response = engine.execute(request);
				} catch (Exception e) {
					//TODO: TranMain应自己完成错误捕获以及rspcode和rspmsg的生成
					LOGGER.error(e);
					DataObject retMsg = new DataObject(request);
					if (e instanceof CbsRunTimeException) {
						retMsg.setString("rspcode", ((CbsRunTimeException) e).getRspCode());
						retMsg.setString("rspmsg", ((CbsRunTimeException) e).getMessage());
					} else {
						retMsg.setString("rspcode", ERR_RSP_CODE);
						retMsg.setString("rspmsg", ExceptionUtils.getErrorMessage(ERR_RSP_CODE, e.getMessage()));
					}
					SendQueueUtil.sendDataObjectByJson(outQueueName, retMsg, options);
					return;
				}
				SendQueueUtil.sendDataObjectByJson(outQueueName, response, options);
			} catch (JMSException e) {
				LOGGER.error(e);
			}

		}

	}

	public Map<String, String> getOutQueueMap() {
		return outQueueMap;
	}

	public void setOutQueueMap(Map<String, String> outQueueMap) {
		this.outQueueMap = outQueueMap;
	}

	public TranMain getEngine() {
		return engine;
	}

	public void setEngine(TranMain engine) {
		this.engine = engine;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("nodeList：%s", nodeList);
		LOGGER.info("outQueueList：%s", outQueueList);

		String[] nodeArray=nodeList.split(",");
		String[] outQueueArray=outQueueList.split(",");
		if (nodeArray.length != outQueueArray.length) {
			throw new Exception("nodeList与outQueueList不匹配！");
		}
		outQueueMap = new HashMap<>(nodeArray.length);
		String node = null;
		String queue = null;
		for (int i = 0; i < nodeArray.length; i++) {
			node = nodeArray[i];
			queue = outQueueArray[i];
			outQueueMap.put(node, queue);
		}
	}

	public String getNodeList() {
		return nodeList;
	}

	public void setNodeList(String nodeList) {
		this.nodeList = nodeList;
	}

	public String getOutQueueList() {
		return outQueueList;
	}

	public void setOutQueueList(String outQueueList) {
		this.outQueueList = outQueueList;
	}

}
