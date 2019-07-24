package cn.com.git.cbs.client;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;

import cn.com.git.cbs.codec.MessageCodec;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.message.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/***
 * 测试用客户端
 * 
 * @author DengJia
 * 
 */
public class Client {
	private static final PlatformLogger LOGGER = PlatformLogger.create();

	private final ExecutorService mScheduler = Executors.newCachedThreadPool();

	private Bootstrap mBootstrap = new Bootstrap();
	private NioEventLoopGroup mEventLoopGroup;
	private Channel mChannel;
	private final Message msg;
	private final String host;
	
	@Value("${testServicePort}")
	private int testServicePort = 8090; //测试客户端端口

	/***
	 * 构造器
	 * 
	 * @param msg
	 *            需要自动发送的消息对象
	 */
	public Client(final String host, final Message msg) {
		this.host=host;
		this.msg = msg;
		ChannelInitializer<Channel> initializer = new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel aCh) throws Exception {
				aCh.pipeline().addLast(new MessageCodec());
				aCh.pipeline().addLast(new ClientHandler()); // BizInvoke
			}
		};
		mEventLoopGroup = new NioEventLoopGroup();
		mBootstrap.group(mEventLoopGroup).channel(NioSocketChannel.class)
				.handler(initializer);

	}

	public void start() {
		ChannelFuture future = mBootstrap.connect(new InetSocketAddress(
				host, testServicePort));
		mChannel = future.channel();
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture aChannelFuture)
					throws Exception {
				if (aChannelFuture.isSuccess()) {
					LOGGER.debug("Connection established");
					mChannel.writeAndFlush(msg);
				} else {
					LOGGER.debug("Connection attempt failed");
					LOGGER.error(aChannelFuture.cause());
				}
			}
		});

		try {
			mChannel.closeFuture().sync();
			LOGGER.debug("Connection closed. Client shutting down.");
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.debug("Client shutdown complete.");
	}

	@SuppressWarnings("unused")
	private void shutdown() {
		try {
			mEventLoopGroup.shutdownGracefully().sync();
			mScheduler.shutdownNow();
			mScheduler.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.error("interrupted while waiting for timeout tasks termination");
		}
	}
}
