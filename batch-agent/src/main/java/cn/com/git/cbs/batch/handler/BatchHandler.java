/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.batch.handler;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import cn.com.git.cbs.batch.message.BatchRequest;
import cn.com.git.cbs.batch.message.BatchResponse;
import cn.com.git.cbs.log.PlatformLogger;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 批处理执行
 * 
 * @author DengJia
 *
 */
@Sharable
@Component
public class BatchHandler extends ChannelInboundHandlerAdapter {
	private final static PlatformLogger LOGGER = PlatformLogger.create();

	@Autowired
	@Qualifier("bizExecutor")
	private ListeningExecutorService bizExecutor;

	/**
	 * 读取通道 异步调用BatchAsyncTask,并将返回值写入ctx
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg != null) {
			LOGGER.debug("recieved msg:" + msg);
			if (msg instanceof BatchRequest) {
				BatchRequest request = (BatchRequest) msg;
				ListenableFuture<BatchResponse> future = bizExecutor.submit(new BatchAsyncTask(request));
				Futures.addCallback(future, new FutureCallback<BatchResponse>() {
					@Override
					public void onSuccess(@Nullable BatchResponse result) {
						// TODO Auto-generated method stub
						if (result == null) {
							result = new BatchResponse(request);
							result.setRspCode("BA9999");
							result.setRspMsg("未知错误！");
						}
						LOGGER.debug("Send Response:[%s]", result.toString());
						ctx.writeAndFlush(result);
					}

					@Override
					public void onFailure(Throwable t) {
						LOGGER.error(t);
						BatchResponse result = new BatchResponse(request);
						result.setRspCode("BA9999");
						result.setRspMsg(t.getMessage());
						ctx.writeAndFlush(result);
					}
				}, bizExecutor);
			} else {
				BatchRequest request = (BatchRequest) msg;
				BatchResponse ret = new BatchResponse(request);
				ret.setRspCode("BA9998");
				ret.setRspMsg("不支持的命令");
				LOGGER.debug("Send Response:[%s]", ret.toString());
				// responder.writeReponse(ret);
				ctx.writeAndFlush(ret);
			}
		}
	}
}
