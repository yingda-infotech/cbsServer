package cn.com.git.cbs.platform.utils.test;

import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.cbs.log.AppLogger;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.test.BaseTest;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class LogUtilsTest extends BaseTest {

	@Test
	public void testAppLog() {
		AppLogger appLog = AppLogger.create();
		appLog.log("%s %d %f", "hello", 100, 33.2);
//		TranMainCtx.getDataPool().setCommonValue("branch", "123456");
//		TranMainCtx.getDataPool().setCommonValue("user", "user123");
		appLog.log("有机构有柜员");
		PlatformLogger pltLog = PlatformLogger.create();
		pltLog.log("%5s %10d %.2f", "a", 12345, 32.098);
	}
}
