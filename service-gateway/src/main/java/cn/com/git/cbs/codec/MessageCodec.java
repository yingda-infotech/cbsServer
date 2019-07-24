package cn.com.git.cbs.codec;

import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;

import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.message.Message;
import cn.com.git.cbs.message.MessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

/***
 * 消息编解码器类
 * 
 * <pre>
 * 用于netty的字节流与Message对象之间的编码与解码，Message对象报文体的xml字符串的编解码工作由平台自身完成。
 * </pre>
 * 
 * @author DengJia
 **/
public class MessageCodec extends ByteToMessageCodec<Message> {

	private static final PlatformLogger LOGGER = PlatformLogger.create();

	private enum State {
		Header, Body
	}

	private int msgLen;
	private String macValue;
	private String tranCode;
	private String srvName;
	private String msgType;
	private String fileFlag;
	private String structType;

	private State state = State.Header;

	private final static String HEADER_BEGIN_FLAG = "{H:";
	private final static String HEADER_END_FLAG = "}\r\n";
	private final static String HEADER_FORMAT = "%3s%08d%2.2s%3.3s%6.6s%1.1s%32.32s%8.8s%3s";
	private final static Pattern pattern;
	private final static Charset encoding = Charset.forName("GBK");
	private final static int lenLimit = 1000000000;

	static {
		String regexp = "\\{H:(\\d{8})(.{2})(.{3})(.{6})(.{1})(.{32})(.{8})\\}\\r\\n";
		pattern = Pattern.compile(regexp);
	}

	/***
	 * 报文编码
	 * 
	 * @see ByteToMessageCodec#encode
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out)
			throws Exception {
		MessageHeader header = msg.getHeader();
		byte[] body = msg.getBodyData().getBytes(encoding);
		int len = body.length;
		if (len >= lenLimit) {
			LOGGER.error("报文长度超出限制！len=[%d]", len);
			throw new Exception("报文长度超出限制！" + len);
		}
		String headerStr = String.format(HEADER_FORMAT, HEADER_BEGIN_FLAG, len,
				header.getMsgType(), header.getStructType(),
				header.getTranCode(), header.getFileFlag(),
				header.getMacValue(), header.getSrvName(), HEADER_END_FLAG);
		out.writeBytes(headerStr.getBytes(encoding));
		out.writeBytes(body);
	}

	/***
	 * 报文解码
	 * 
	 * @see ByteToMessageCodec#decode
	 */
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
			List<Object> out) throws Exception {
		msg.markReaderIndex();
		// 判断是否可读，不可读要重置read的索引
		if (!msg.isReadable()) {
			msg.resetReaderIndex();
			return;
		}
		switch (state) {
		case Header:
			if (msg.readableBytes() < 66) {
				msg.resetReaderIndex();
				return;
			}
			CharSequence strHeader = msg.readCharSequence(66, encoding);
			Matcher regexMatcher = pattern.matcher(strHeader);
			if (regexMatcher.matches()) {
				msgLen = NumberUtils.toInt(regexMatcher.group(1));
				if (msgLen <= 0) {
					msg.skipBytes(msg.readableBytes());
					LOGGER.error("报文格式异常：报文长度非法！msgLen=[%d]", msgLen);
					throw new Exception("报文格式异常：报文长度非法！" + msgLen);
				}
				msgType = regexMatcher.group(2);
				structType = regexMatcher.group(3);
				tranCode = regexMatcher.group(4);
				if (StringUtils.isBlank(tranCode)) {
					msg.skipBytes(msg.readableBytes());
					throw new Exception("报文格式异常：交易代码为空！");
				}
				fileFlag = regexMatcher.group(5);
				macValue = regexMatcher.group(6);
				srvName = regexMatcher.group(7);
				LOGGER.debug("报文头获取：%s", strHeader);
				state = State.Body;
				return;
			} else {
				msg.skipBytes(msg.readableBytes());
				LOGGER.error("报文格式异常：报文头格式不正确！strHeader=[%s]", strHeader);
				throw new Exception("报文格式异常：报文头格式不正确！" + strHeader);
			}
		case Body:
			if (msg.readableBytes() < msgLen) {
				LOGGER.debug("报文体长度不足：[%d]", msg.readableBytes());
				msg.resetReaderIndex();
				return;
			}
			LOGGER.debug("读取报文体");
			String strBody = msg.readCharSequence(msgLen, encoding).toString();
			Message inMsg = new Message();
			MessageHeader header = new MessageHeader(msgType, structType,
					tranCode, fileFlag, macValue, srvName);
			inMsg.setHeader(header);
			inMsg.setBodyData(strBody);
			out.add(inMsg);
			LOGGER.debug("读取报文成功");
			state = State.Header;
			return;
		}
	}
}
