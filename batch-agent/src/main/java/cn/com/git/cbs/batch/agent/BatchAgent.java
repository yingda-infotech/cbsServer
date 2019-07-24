/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.batch.agent;

import java.net.InetSocketAddress;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.git.cbs.batch.codec.BatchCommandDecoder;
import cn.com.git.cbs.batch.codec.BatchRequestDecoder;
import cn.com.git.cbs.batch.codec.BatchResponseEncoder;
import cn.com.git.cbs.batch.handler.BatchResponseOutput;
import cn.com.git.cbs.batch.handler.BatchHandler;
import cn.com.git.cbs.batch.message.BatchResponse;
import cn.com.git.cbs.log.PlatformLogger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 批处理Agent Java实现，目前仅实现了批处理作业中调起Java批处理任务的功能
 * 
 * @author DengJia
 *
 */
@Component
public class BatchAgent {
	private final static PlatformLogger LOGGER = PlatformLogger.create();

	private ServerBootstrap mBootstrap;
	@Autowired
	@Qualifier("bossGroup")
	private NioEventLoopGroup bossGroup;
	@Autowired
	@Qualifier("workerGroup")
	private NioEventLoopGroup workerGroup;
	@Autowired
	private BatchHandler batchHandler; // 批处理Handler
	@Value("${batchAgent.port}")
	private int port = 9090; // 端口
	@Value("${batch.uphost}")
	private String remoteHost;
	@Value("${batch.upport}")
	private int remotePort;
	@Autowired
	private BatchRequestDecoder batchRequestDecoder;
	@Autowired
	private BatchResponseEncoder batchResponseEncoder;

	/**
	 * 默认构造器
	 */
	public BatchAgent() {
		mBootstrap = new ServerBootstrap();
		mBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		mBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
	}

	/***
	 * Agent启动
	 */
	public void start() {
		ChannelInitializer<Channel> initializer = new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel channel) throws Exception {
				// TODO Auto-generated method stub
				channel.pipeline().addLast(new BatchResponseOutput(remoteHost, remotePort));
				channel.pipeline().addLast(new BatchCommandDecoder());
				channel.pipeline().addLast(batchResponseEncoder);
				channel.pipeline().addLast(batchRequestDecoder);
				channel.pipeline().addLast(batchHandler); // BizInvoke
			}
		};
		mBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(initializer)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
		LOGGER.info("Batch Agent bind on port:%d", port);
		ChannelFuture future = mBootstrap.bind(new InetSocketAddress(this.port));
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture aChannelFuture) throws Exception {
				if (aChannelFuture.isSuccess()) {
					LOGGER.info("Batch Agent startup on %d succeed!", port);
				} else {
					LOGGER.error("Batch Agent startup failed!", aChannelFuture.cause());
				}
			}
		});
		try {
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
	}

	@PreDestroy
	public void shutdown() {
		try {
			bossGroup.shutdownGracefully().sync();
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
	}
}
