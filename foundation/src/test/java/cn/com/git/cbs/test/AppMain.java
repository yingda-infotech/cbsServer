package cn.com.git.cbs.test;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.test.BaseTest;
import cn.com.git.cbs.platform.utils.JSONUtils;

public class AppMain extends BaseTest {
	@Test
	@Transactional(propagation = Propagation.NEVER)
	public void test() {
		DataObject test=new DataObject();
		test.set("test","1\r\n\"2\"");
		String jsonStr=JSONUtils.dataObject2String(test);
		System.out.println(jsonStr);
		System.out.println(JSONUtils.string2DataObject(jsonStr).get("test"));
	}
}