package cn.com.git.cbs.batch.handler;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import cn.com.git.cbs.log.PlatformLogger;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;

public class BatchResponseOutput extends ChannelOutboundHandlerAdapter {
	private final static int RETRYCOUNT = 3;
	private static final PlatformLogger LOGGER = PlatformLogger.create();

	private final String remoteHost;
	private final int remotePort;
	private Channel outboundChannel;
	private EventLoop eventLoop;
	private Class channelClass;

	private ChannelInitializer<Channel> initializer = new ChannelInitializer<Channel>() {
		@Override
		protected void initChannel(Channel aCh) throws Exception {
			// aCh.pipeline().addLast(new BatchResponseEncoder());
		}
	};

	public BatchResponseOutput(String remoteHost, int remotePort) {
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerAdded(ctx);
		final Channel inboundChannel = ctx.channel();

		this.eventLoop = inboundChannel.eventLoop();
		this.channelClass = inboundChannel.getClass();
		// Start the connection attempt.
		doConnect(remoteHost, remotePort, 0);
	}

	private ChannelFuture doConnect(String host, int port, int retryCount) {
		Bootstrap b = new Bootstrap();
		b.group(eventLoop).channel(this.channelClass).handler(initializer).option(ChannelOption.SO_KEEPALIVE, true);
		ChannelFuture f = b.connect(remoteHost, remotePort);
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture aChannelFuture) throws Exception {
				if (aChannelFuture.isSuccess()) {
					LOGGER.debug("Batch Agent connect to [%s]:[%d] success", remoteHost, remotePort);
					outboundChannel = aChannelFuture.channel();
					// setOutboundChannel(aChannelFuture.channel());
				} else {
					LOGGER.error("Batch Agent connect to [%s]:[%d] failed", remoteHost, remotePort,
							aChannelFuture.cause());
					if (retryCount > 0) {
						eventLoop.schedule(new Callable<ChannelFuture>() {
							@Override
							public ChannelFuture call() {
								return doConnect(host, port, retryCount - 1);
							}
						}, 1L, TimeUnit.SECONDS);
					} else {
						LOGGER.fatal("Batch Agent failed to Connect to [%s]:[%d]", remoteHost, remotePort);
					}
				}
			}
		});
		return f;
	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		// super.flush(ctx);
		outboundChannel.flush();
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if (outboundChannel == null || !outboundChannel.isActive()) {
			LOGGER.debug("disconnected, try to reconnect");
			doConnect(remoteHost, remotePort, RETRYCOUNT).addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					outboundChannel.writeAndFlush(msg);
				}
			});
		} else {
			outboundChannel.writeAndFlush(msg);
		}
	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.disconnect(ctx, promise);
		outboundChannel.disconnect();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		if (outboundChannel != null && outboundChannel.isActive()) {
			outboundChannel.close().syncUninterruptibly();
		}
		// outboundChannel.eventLoop().shutdownGracefully();
		// outboundChannel.closeFuture().syncUninterruptibly();
	}
}
