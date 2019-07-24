package cn.com.git.cbs.batch.codec;

import java.nio.charset.Charset;

import org.springframework.stereotype.Component;

import cn.com.git.cbs.batch.message.BatchResponse;
import cn.com.git.cbs.boot.conf.BatchAgentConf;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.KeyValueStringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 批处理任务的编码
 * 
 * @author DengJia
 *
 */
@Sharable
@Component
public class BatchResponseEncoder extends MessageToByteEncoder<BatchResponse> {
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	private final static Charset ENCODING = BatchAgentConf.ENCODING;

	/**
	 * 编码 把批处理调度响应的结构转换成xml结构的字符串，按照报文头和报文体的方式发送
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, BatchResponse msg, ByteBuf out) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(msg.getCommand()).append("RS");
		sb.append(KeyValueStringUtils.toKeyPairString(msg));
		String ret = sb.toString();
		byte[] retBytes = ret.getBytes(ENCODING);
		String prefix = String.format("%08d", retBytes.length);
		LOGGER.debug("输出报文：%s%s", prefix, ret);
		out.writeBytes(prefix.getBytes(ENCODING));
		out.writeBytes(ret.getBytes(ENCODING));
	}
}
