/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.platform.utils.test;

import org.junit.Test;

import cn.com.git.cbs.platform.test.BaseTest;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * @author DengJia
 *
 */
@SuppressWarnings("all")
public class ExceptionUtilsTest extends BaseTest {
	@Test
	public void test() {
		System.out.println(ExceptionUtils.returnError("PL9999", null).getMessage());
		System.out.println(ExceptionUtils.returnError("DB0001", null,-8789,"测试异常").getMessage());
		System.out.println(ExceptionUtils.returnError("DB0002", null,"SubInput").getMessage());
		
	}
}
