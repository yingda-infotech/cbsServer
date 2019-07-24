package cn.com.git.cbs.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.CharsetUtil;

import org.junit.Assert;
import org.junit.Test;

import cn.com.git.cbs.common.utils.FileUtils;
import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.message.Message;
import cn.com.git.cbs.message.MessageHeader;

public class CodecTest {
	@Test
	public void testEncode() {
		Message bizMsg = new Message();
		MessageHeader header = new MessageHeader("RQ", "XML", "A10010", "0",
				"M123456789012345678901234567890C", "123456");
		bizMsg.setHeader(header);
		String bizBody = FileUtils.readTextResource("/tranmsg.xml", "UTF-8");
		bizMsg.setBodyData(bizBody);
		int length = bizBody.getBytes(CharsetUtil.UTF_8).length;
		EmbeddedChannel channel = new EmbeddedChannel(new MessageCodec());
		Assert.assertTrue(channel.writeOutbound(bizMsg));
		ByteBuf encoded = (ByteBuf) channel.readOutbound();
		Assert.assertNotNull(encoded);
		String headerStr = String.format(
				"%3s%08d%2.2s%3.3s%6.6s%1.1s%32.32s%8.8s%3s", "{H:", length,
				header.getMsgType(), header.getStructType(),
				header.getTranCode(), header.getFileFlag(),
				header.getMacValue(), header.getSrvName(), "}\r\n");
		System.out.println(headerStr + bizBody);
		Assert.assertEquals(headerStr,
				encoded.readCharSequence(66, CharsetUtil.UTF_8));
		Assert.assertEquals(bizBody, encoded.toString(CharsetUtil.UTF_8));
	}

	@Test
	public void testDecode() {
		EmbeddedChannel channel = new EmbeddedChannel(new MessageCodec());
		String bizBody = FileUtils.readTextResource("/tranmsg.xml", "UTF-8");
		int msgLen = bizBody.getBytes(CharsetUtil.UTF_8).length;
		String macValue = "C123456789012345678901234567890M";
		String tranCode = "T1004C";
		String srvName = "SRVNAME1";
		String msgType = "RS";
		String fileFlag = "0";
		String structType = "XML";
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeBytes("{H:".getBytes());
		buffer.writeBytes(StringUtils.leftPad(String.valueOf(msgLen), 8, "0")
				.getBytes());
		buffer.writeBytes(msgType.getBytes());
		buffer.writeBytes(structType.getBytes());
		buffer.writeBytes(tranCode.getBytes());
		buffer.writeBytes(fileFlag.getBytes());
		buffer.writeBytes(macValue.getBytes());
		buffer.writeBytes(srvName.getBytes());
		buffer.writeBytes("}\r\n".getBytes());
		buffer.writeBytes(bizBody.getBytes());
		Assert.assertTrue(channel.writeInbound(buffer));
		Message response = (Message) channel.readInbound();
		// System.out.println(response);
		assertMessage(response, macValue, tranCode, srvName, msgType, fileFlag,
				structType, bizBody);
	}

	private void assertMessage(Message response, String macValue,
			String tranCode, String srvName, String msgType, String fileFlag,
			String structType, String bizBody) {
		MessageHeader header = response.getHeader();
		Assert.assertEquals(tranCode, header.getTranCode());
		Assert.assertEquals(macValue, header.getMacValue());
		Assert.assertEquals(bizBody, response.getBodyData());
	}
}
