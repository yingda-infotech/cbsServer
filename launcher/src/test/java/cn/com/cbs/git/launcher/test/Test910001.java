/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.cbs.git.launcher.test;

import org.junit.Test;

import cn.com.git.cbs.client.Client;
import cn.com.git.cbs.common.utils.FileUtils;
import cn.com.git.cbs.message.Message;
import cn.com.git.cbs.message.MessageHeader;

/**
 * @author DengJia
 *
 */
public class Test910001 {

	//@Test
	public void test() {
		MessageHeader header = new MessageHeader("RQ", "XML", "910001", "0",
				"################################", "CBS");
		Message req = new Message();
		req.setHeader(header);
		String xmlData = FileUtils.readTextResource("/910001RQ.xml", "UTF-8");
		req.setBodyData(xmlData);
		new Client("127.0.0.1",req).start();
	}
	
	@Test
	public void testJSON() {
		MessageHeader header = new MessageHeader("RQ", "JSN", "910001", "0",
				"################################", "CBS");
		Message req = new Message();
		req.setHeader(header);
		String jsonData = FileUtils.readTextResource("/910001RQ.json", "UTF-8");
		req.setBodyData(jsonData);
		new Client("127.0.0.1",req).start();
	}
}
