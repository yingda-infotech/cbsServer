/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.platform.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath*:/META-INF/applicationContext*.xml")
@Transactional(transactionManager = "transactionManager")  

/**
 * 所有的测试的基类
 * @author DengJia
 *
 */
public abstract class BaseTest {
	
}