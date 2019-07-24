package cn.com.git.cbs.connector;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import cn.com.git.cbs.codec.MessageCodec;
import cn.com.git.cbs.handler.MessageToQueueHandler;
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

/***
 *  联机交易平台Netty通讯层，通过Queue转接tpmanager
 * 
 * @author DengJia
 *
 */
//@Component()
//@Profile({"asyncExecutor","asyncConnector"})
public class NettyQueueConnector{
	private static final PlatformLogger LOGGER = PlatformLogger.create();

	private ServerBootstrap mBootstrap;
	@Autowired
	@Qualifier("bossGroup")
	private NioEventLoopGroup bossGroup;
	@Autowired
	@Qualifier("workerGroup")
	private NioEventLoopGroup workerGroup;
	@Autowired
	private MessageToQueueHandler bizHandler; // 业务处理Handler
	@Value("${gateway.port}")
	private int port = 8090; // 端口

	/***
	 * 默认构造器
	 */
	public NettyQueueConnector() {
		mBootstrap = new ServerBootstrap();
		mBootstrap.option(ChannelOption.ALLOCATOR,
				PooledByteBufAllocator.DEFAULT);
		mBootstrap.childOption(ChannelOption.ALLOCATOR,
				PooledByteBufAllocator.DEFAULT);
	}

	/***
	 * 服务器启动
	 */
	@PostConstruct
	public void start() {
		ChannelInitializer<Channel> initializer = new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel channel) throws Exception {
				// TODO Auto-generated method stub
				channel.pipeline().addLast(new MessageCodec());
				channel.pipeline().addLast(bizHandler); // BizInvoke
			}
		};

		mBootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(initializer);
		LOGGER.info("Server bind on port:%d", port);
		ChannelFuture future = mBootstrap
				.bind(new InetSocketAddress(this.port));
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture aChannelFuture)
					throws Exception {
				if (aChannelFuture.isSuccess()) {
					LOGGER.info("Server startup on %d succeed!", port);
				} else {
					LOGGER.error("Server startup failed!",
							aChannelFuture.cause());
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
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public MessageToQueueHandler getBizHandler() {
		return bizHandler;
	}

	public void setBizHandler(MessageToQueueHandler bizHandler) {
		this.bizHandler = bizHandler;
	}

}
