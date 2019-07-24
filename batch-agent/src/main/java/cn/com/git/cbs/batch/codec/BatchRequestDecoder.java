package cn.com.git.cbs.batch.codec;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.git.cbs.batch.internal.BatchCommand;
import cn.com.git.cbs.batch.message.BatchRequest;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.KeyValueStringUtils;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * 请求报文解析为map，放到list中
 * 
 * @author DengJia
 *
 */
@Sharable
@Component
public class BatchRequestDecoder extends MessageToMessageDecoder<BatchCommand> {
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	private final static String TRANCODE = "CC1001";
	// private final static String REGEXP =
	// "<ProgName>(\\w+)<\\/><Para>(\\w+)<\\/>";
	// private final static Pattern PATTERN = Pattern.compile(REGEXP,
	// Pattern.MULTILINE);

	/**
	 * 解码 把批处理报文字符串专成CC1001RQ对象存入list中
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, BatchCommand msg, List<Object> out) throws Exception {
		if (!StringUtils.equals(TRANCODE, msg.getCommand())) {
			LOGGER.warn("不支持的报文类型:[%s]", msg.getCommand());
		}
		BatchRequest inMessage =new BatchRequest();
		inMessage.setCommand(msg.getCommand());
		inMessage.setData(KeyValueStringUtils.toMap(msg.getCommandBody()));
		out.add(inMessage);
	}
}
