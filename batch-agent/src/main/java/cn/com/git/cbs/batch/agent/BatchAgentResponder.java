package cn.com.git.cbs.batch.agent;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.monitor.annotation.MField;

import cn.com.git.cbs.batch.codec.BatchResponseEncoder;
import cn.com.git.cbs.batch.message.BatchResponse;
import cn.com.git.cbs.log.PlatformLogger;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 批处理Agent Java响应
 * 
 * @author DengJia
 *
 */
@Deprecated
public class BatchAgentResponder {
	private static final PlatformLogger LOGGER = PlatformLogger.create();
	@Value("${batch.uphost}")
	private String host;
	@Value("${batch.upport}")
	private int port;
	@Autowired
	@Qualifier("workerGroup")
	private NioEventLoopGroup mEventLoopGroup;
	private Channel mChannel;

	@Autowired
	private BatchResponseEncoder encoder;
	private ChannelInitializer<Channel> initializer=new ChannelInitializer<Channel>(){

	@Override protected void initChannel(Channel aCh)throws Exception{aCh.pipeline().addLast(encoder);}};

	/**
	 * 写入和刷新批处理调度返回的结果
	 * 
	 * @param response
	 *            批处理调度返回的结果
	 */
	public synchronized void writeReponse(BatchResponse response) {
		try {
			if (mChannel == null ||  !mChannel.isActive()) {
				connectAndWrite(response);
			} else {
				mChannel.writeAndFlush(response);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * 连接通道
	 * @throws Exception 
	 */
	public void connectAndWrite(BatchResponse response) throws Exception {
		LOGGER.debug("Batch Agent try connect to [%s]:[%d]",host,port);
		Bootstrap mBootstrap = new Bootstrap();
		mBootstrap.group(mEventLoopGroup).channel(NioSocketChannel.class).handler(initializer);
		ChannelFuture channelFuture=mBootstrap.connect(new InetSocketAddress(host, port));
		channelFuture.addListener(new  ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture aChannelFuture) throws Exception {
				if (aChannelFuture.isSuccess()) {
					LOGGER.debug("Batch Agent connect to [%s]:[%d] success",host,port);
					mChannel=aChannelFuture.channel();
					mChannel.writeAndFlush(response);
				} else {
					LOGGER.error("Batch Agent connect to NLS failed", aChannelFuture.cause());
				}
			}
		});
	}

	/**
	 * 关闭通道
	 */
	@Override
	protected void finalize() {
		if (mChannel != null && mChannel.isActive()) {
			mChannel.close().syncUninterruptibly();
		}
		mEventLoopGroup.shutdownGracefully();
		mChannel.closeFuture().syncUninterruptibly();
		mEventLoopGroup = null;
		mChannel = null;
	}

}
