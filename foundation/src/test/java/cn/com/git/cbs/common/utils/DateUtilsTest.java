package cn.com.git.cbs.common.utils;

import org.junit.Test;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.test.BaseTest;

public class DateUtilsTest extends BaseTest {
	private static final PlatformLogger LOGGER = PlatformLogger.create();
	
//	@Test
	public void addDays(){  
		LOGGER.info(DateUtils.addDays("2017-02-08", 300));
	}
	
//	@Test
	public void subDays(){  
		LOGGER.info(DateUtils.subDays("2017-02-08", 300));
	}
	
//	@Test
	public void addYears(){  
		LOGGER.info(DateUtils.addYears("2017-02-08", 300));
		LOGGER.info(DateUtils.addYears("2017-02-08", -300));
	}
	
	@Test
	public void dateDiff(){  
		LOGGER.info(DateUtils.dateDiff("2017-04-08", "2017-03-08"));
	}

}
