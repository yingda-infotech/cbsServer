package cn.com.git.cbs.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import cn.com.git.cbs.dto.MessageDTOFactory;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.message.Message;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.mq.SendQueueUtil;

/***
 * 消息处理器，用于将转码后的Message对象使用线程池传递给平台处理
 * 
 * @author DengJia
 * 
 */
@Sharable
//@Component
//@Profile({"asyncExecutor","asyncConnector"})
public class MessageToQueueHandler extends ChannelInboundHandlerAdapter {
	private static final PlatformLogger LOGGER = PlatformLogger.create();
	/***
	 * 执行线程池
	 */
	@Autowired
	@Qualifier("bizExecutor")
	private ThreadPoolTaskExecutor executor;

	@Value("${NettyQ.inQueueName}")
	private String inQueueName;

	@Value("${NettyQ.clientID}")
	private String clientID;

	@Autowired
	private MessageDTOFactory factory;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		if (msg != null && msg instanceof Message) {
			LOGGER.debug("recieved msg:" + msg);
			executor.execute(new BizTask((Message) msg, clientID, ctx, factory, inQueueName));
		}
	}
	
	@PreDestroy
	public void shutdown() {
		executor.setAwaitTerminationSeconds(5);
		executor.shutdown();
	}
}

class BizTask implements Runnable {
	private Message request;
	private String clientID;
	private ChannelHandlerContext ctx;
	private MessageDTOFactory factory;
	private String inQueueName;

	BizTask(Message request, String clientID, ChannelHandlerContext ctx, MessageDTOFactory factory,
			String inQueueName) {
		this.request = request;
		this.clientID = clientID;
		this.ctx = ctx;
		this.factory = factory;
		this.inQueueName = inQueueName;
	}

	public void run() {
		// 将消息传入Queue中，并将ctx与messageID放入对照表中
		String coreleationID = String.valueOf(CtxHolder.id.incrementAndGet());
		Map<String, String> options = new HashMap<>();
		options.put("clientID", clientID); 
		options.put("coreleationID", coreleationID);        
		CtxHolder.ctxMap.put(coreleationID, ctx);               
		// 将Message转换成DataObject
		DataObject requestObj = factory.getDecoder(request.getHeader().getTranCode(), request.getHeader().getStructType())
				.decode(request.getBodyData());  
		requestObj.importObject(request.getHeader());
		SendQueueUtil.sendDataObjectByJson(inQueueName, requestObj, options);
	}
}