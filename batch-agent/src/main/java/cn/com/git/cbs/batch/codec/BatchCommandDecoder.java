package cn.com.git.cbs.batch.codec;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.com.git.cbs.batch.internal.BatchCommand;
import cn.com.git.cbs.boot.conf.BatchAgentConf;
import cn.com.git.cbs.log.PlatformLogger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 批处理调度解报文
 * 
 * @author DengJia
 *
 */
public class BatchCommandDecoder extends ByteToMessageDecoder {
	private final static PlatformLogger LOGGER = PlatformLogger.create();

	/**
	 * 定义一个枚举类型 State 内容 Header, Body
	 */
	private enum State {
		Header, Body
	}

	private int msgLen;
	private final static Charset encoding = BatchAgentConf.ENCODING;
	private State state = State.Header;

	/**
	 * 解码 获取ByteBuf格式的报文，解析报文后存入list中
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		msg.markReaderIndex();
		// 判断是否可读，不可读要重置read的索引
		if (!msg.isReadable()) {
			msg.resetReaderIndex();
			return;
		}
		switch (state) {
		case Header:
			if (msg.readableBytes() < 8) {
				msg.resetReaderIndex();
				return;
			}
			String strLen = msg.readCharSequence(8, encoding).toString();
			msgLen = NumberUtils.toInt(strLen);
			if (msgLen <= 0) {
				msg.skipBytes(msg.readableBytes());
				LOGGER.error("报文格式异常：报文长度非法！msgLen=[%d]", msgLen);
				throw new Exception("报文格式异常：报文长度非法！" + msgLen);
			}
			// LOGGER.debug("报文头获取：%s", strLen);
			state = State.Body;
			return;
		case Body:
			if (msg.readableBytes() < msgLen) {
				// LOGGER.debug("报文体长度不足：[%d]", msg.readableBytes());
				msg.resetReaderIndex();
				return;
			}
			// LOGGER.debug("读取报文体");
			String strBody = msg.readCharSequence(msgLen, encoding).toString();
			BatchCommand inMessage = new BatchCommand();
			String command = StringUtils.left(strBody, 6);
			if (StringUtils.isEmpty(command)) {
				msg.skipBytes(msg.readableBytes());
				LOGGER.error("报文格式异常：报文中没有交易代码！%s", strBody);
				state = State.Header;
				throw new Exception("报文格式异常：报文中没有交易代码！");
			}
			inMessage.setCommand(command);
			String type = StringUtils.mid(strBody, 6, 2);
			if (!StringUtils.equalsIgnoreCase("RQ", type)) {
				LOGGER.error("报文格式异常：错误的报文类型[%s]！", type);
				state = State.Header;
				throw new Exception("报文格式异常：错误的报文类型！");
			}
			inMessage.setCommandBody(StringUtils.substring(strBody, 8));
			out.add(inMessage);
			// LOGGER.debug("读取报文成功");
			LOGGER.debug("输入报文：%08d%s", msgLen, strBody);
			state = State.Header;
			return;
		}
	}
}