package cn.com.git.cbs.handler;

import java.util.concurrent.Callable;

import cn.com.git.cbs.dto.MessageDTOFactory;
import cn.com.git.cbs.exception.CbsRunTimeException;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.message.Message;
import cn.com.git.cbs.message.MessageHeader;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.ExceptionUtils;
import cn.com.git.cbs.tpmanager.TranMain;

/***
 * 消息调用任务
 * 
 * @author DengJia
 *
 */
public class TransInvokeTask implements Callable<Message> {

	private final static PlatformLogger LOGGER = PlatformLogger.create();
	private final static String ERR_RSP_CODE = "PL9999";

	private Message msg; // 消息
	private TranMain engine; // 平台实例
	private MessageDTOFactory factory;

	/***
	 * 默认构造器
	 * 
	 * @param engine
	 *            平台实例
	 * @param msg
	 *            消息对象
	 */
	public TransInvokeTask(TranMain engine, Message msg, MessageDTOFactory factory) {
		this.engine = engine;
		this.msg = msg;
		this.factory = factory;
	}


	@Override
	public Message call() throws Exception {
		// 将Message转换成DataObject
		DataObject requestObj = factory.getDecoder(msg.getHeader().getTranCode(), msg.getHeader().getStructType())
				.decode(msg.getBodyData());
		requestObj.importObject(msg.getHeader());
		DataObject response = null;

		try {
			response = engine.execute(requestObj);
		} catch (Exception e) {
			// TODO: TranMain应自己完成错误捕获以及rspcode和rspmsg的生成
			LOGGER.error(e);
			response = new DataObject(requestObj);
			if (e instanceof CbsRunTimeException) {
				response.setString("rspcode", ((CbsRunTimeException) e).getRspCode());
				response.setString("rspmsg", ((CbsRunTimeException) e).getMessage());
			} else {
				response.setString("rspcode", ERR_RSP_CODE);
				response.setString("rspmsg", ExceptionUtils.getErrorMessage(ERR_RSP_CODE, e.getMessage()));
			}
		}

		MessageHeader retHeader = msg.getHeader();
		retHeader.setMsgType("RS");
		// 将返回值转换为Message
		String dataBody = factory.getEncoder(retHeader.getTranCode(), retHeader.getStructType()).encode(response);

		Message ret = new Message();
		ret.setBodyData(dataBody);
		ret.setHeader(retHeader);

		return ret;
	}

}
