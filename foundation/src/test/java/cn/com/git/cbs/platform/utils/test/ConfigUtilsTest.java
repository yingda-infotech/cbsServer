package cn.com.git.cbs.platform.utils.test;

import org.junit.Test;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.test.BaseTest;
import cn.com.git.cbs.platform.utils.ConfigUtils;

public class ConfigUtilsTest  extends BaseTest {
	
	private static final PlatformLogger LOGGER = PlatformLogger.create();
		
	@Test
	public void getErrorPropertyTest() {
		LOGGER.debug(ConfigUtils.getErrorProperty("PL1003"));
		LOGGER.debug(ConfigUtils.getAppProperty("defaultEncoding"));
	}

}
