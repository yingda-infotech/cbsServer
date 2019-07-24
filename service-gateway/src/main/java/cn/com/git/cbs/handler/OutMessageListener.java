package cn.com.git.cbs.handler;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.dto.MessageDTOFactory;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.message.MessageHeader;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.JSONUtils;

//@Component("asyncResponse")
//@Profile({"asyncExecutor","asyncConnector"})
public class OutMessageListener implements MessageListener{
	
	private static final PlatformLogger LOGGER = PlatformLogger.create();
	
	@Autowired
	private MessageDTOFactory factory;  

	@Override   
	public void onMessage(Message inMessage) {   
		// 拿到响应对象以后，根据取出的messageID找到对应的ctx，将cbs的message写出去，并移出对照表
		if (inMessage instanceof TextMessage) {
			try {
				String clientID = inMessage.getStringProperty("clientID");
				String coreleationID = inMessage.getStringProperty("coreleationID");
   
				Map<String, String> options = new HashMap<String, String>();
				options.put("clientID", clientID);
				options.put("coreleationID", coreleationID);

				DataObject response = JSONUtils.string2DataObject(((TextMessage) inMessage).getText());
				
				//将response转化为输出报文
				cn.com.git.cbs.message.Message retMsg= packMessage(response);
				//将cbs Message对象输出至相应的ctx上
				ChannelHandlerContext ctx= CtxHolder.ctxMap.remove(coreleationID);
				if (ctx!=null ) {
					ctx.writeAndFlush(retMsg);
				}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e);
			}

		}
	}
	
	private cn.com.git.cbs.message.Message packMessage(DataObject response) {
		String tranCode = response.getString("trancode");
		String structType = response.getString("structtype");
		String strBody = factory.getEncoder(tranCode, structType).encode(response);
		cn.com.git.cbs.message.Message ret = new cn.com.git.cbs.message.Message();
		ret.setHeader(packHeader(response)); 
		ret.setBodyData(strBody);
		
		return ret;
	}
	
	private MessageHeader packHeader(DataObject response) {
		String tranCode = StringUtils.defaultIfBlank(response.getString("trancode"), "000000");
		String structType = StringUtils.defaultIfBlank(response.getString("structtype"), "JSN");
		String fileFlag = StringUtils.defaultIfBlank(response.getString("fileflag"), "0");
		String macValue = StringUtils.defaultIfBlank(response.getString("macValue"), "");
		String srvName = StringUtils.defaultIfBlank(response.getString("srvname"), "");
		MessageHeader header = new MessageHeader("RS", structType, tranCode, fileFlag, macValue, srvName);
		return header;
	}
}
