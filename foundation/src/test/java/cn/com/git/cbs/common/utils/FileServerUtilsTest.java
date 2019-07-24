package cn.com.git.cbs.common.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.git.cbs.platform.test.BaseTest;

public class FileServerUtilsTest extends BaseTest {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileServerUtilsTest.class);
	
	@Test
	public void uploadFileTest(){  
		boolean bool = FileServerUtils.uploadFile("http://10.100.21.201/upload.do","D:\\workspace3\\wls-api.jar");
		LOGGER.info("uploadFileTest上传结果："+bool);
	}
	

}
