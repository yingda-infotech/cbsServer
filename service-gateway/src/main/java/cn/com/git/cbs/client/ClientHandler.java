package cn.com.git.cbs.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.message.Message;

/**
 * 客户端执行
 * @author DengJia
 *
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
	private static final PlatformLogger LOGGER=PlatformLogger.create();
	
	public ClientHandler(){
		super();
	}
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception {
		LOGGER.info("recived msg");
		LOGGER.info(msg.toString());
		ctx.close().sync();
	}
	

}
