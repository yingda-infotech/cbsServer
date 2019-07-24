package cn.com.git.cbs.client;

import org.junit.Test;

import cn.com.git.cbs.common.utils.FileUtils;
import cn.com.git.cbs.message.Message;
import cn.com.git.cbs.message.MessageHeader;

public class ClientTest {
	@Test
	public void test() {
		Message bizMsg = new Message();
		MessageHeader header = new MessageHeader("RQ", "XML", "110001", "0",
				"M123456789012345678901234567890C", "123456");
		bizMsg.setHeader(header);
		String bizBody = FileUtils.readTextResource("/tranmsg.xml", "UTF-8");
		bizMsg.setBodyData(bizBody);
		// System.out.println(bizBody);
		Client client;
		client = new Client("127.0.0.1",bizMsg);
		client.start();
	}
}
