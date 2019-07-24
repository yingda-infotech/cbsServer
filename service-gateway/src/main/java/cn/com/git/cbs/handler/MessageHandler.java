package cn.com.git.cbs.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import cn.com.git.cbs.dto.MessageDTOFactory;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.message.Message;
import cn.com.git.cbs.tpmanager.TranMain;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/***
 * 消息处理器，用于将转码后的Message对象使用线程池传递给平台处理
 * 
 * @author DengJia
 * 
 */
@Sharable
@Component
// @Profile("directExecutor")
public class MessageHandler extends ChannelInboundHandlerAdapter {
	private static final PlatformLogger LOGGER = PlatformLogger.create();
	/***
	 * 执行线程池
	 */
	@Autowired
	@Qualifier("bizExecutor")
	private ListeningExecutorService bizExecutor;

	/***
	 * 平台实例
	 */
	@Autowired
	@Qualifier("tranMainImpl")
	private TranMain engine;

	@Autowired
	private MessageDTOFactory factory;

	@Value("${bizExecutor.executeTimeout}")
	private long executionTimeout = 3500L;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		if (msg != null && msg instanceof Message) {
			LOGGER.debug("recieved msg:" + msg);
			Message request = (Message) msg;
			ListenableFuture<Message> future = bizExecutor.submit(new TransInvokeTask(engine, request, factory));
			Futures.addCallback(future, new FutureCallback<Message>() {
				@Override
				public void onSuccess(Message result) {
					LOGGER.debug("Send Response:[%s]", result.toString());
					ctx.writeAndFlush(result);
				}

				@Override
				public void onFailure(Throwable t) {
					LOGGER.error(t);
				}
				
			}, bizExecutor);
		}
	}
	//
	// future.addListener(new GenericFutureListener<Future<? super Message>>() {
	// @Override
	// public void operationComplete(Future<? super Message> arg0) throws Exception
	// {
	// Message ret;
	// try {
	// ret = future.get(executionTimeout, TimeUnit.MILLISECONDS);
	// LOGGER.debug("Send Response:[%s]", ret.toString());
	// ctx.writeAndFlush(ret);
	// } catch (InterruptedException e) {
	// LOGGER.error(e);
	// } catch (ExecutionException e) {
	// LOGGER.error(e);
	// } catch (TimeoutException e) {
	// LOGGER.error(e);
	// }
	// }
	// });
	//
	// } catch (RejectedExecutionException ex) {
	// LOGGER.error("执行队列满，拒绝服务！", ex);
	// }
	// }
	// }

	public TranMain getEngine() {
		return engine;
	}

	public void setEngine(TranMain engine) {
		this.engine = engine;
	}

}
