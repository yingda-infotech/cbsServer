package cn.com.git.cbs.dao.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.cbs.engine.dao.TBL_MaintxdefDao;
import cn.com.git.cbs.engine.datamodel.TBL_Maintxdef;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.test.BaseTest;

/**
 * 测试cursor
 * 
 * 测试结果：    for update 方式查询出来的结果，必须cursor查询结果了，其他地方才可以update，
 *(并不是循环出来部分，这部分已经读取的就可以update了，必须等全部完事才可以)
 * 
 * 
 * @author xia
 *
 */

public class TBL_MaintxdefDaoTest extends BaseTest{
	
	@Autowired
	TBL_MaintxdefDao dao;
	
	private static final PlatformLogger LOGGER = PlatformLogger.create();
	
	//模拟查询失败,不在事物内
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	
//	@Transactional(propagation=Propagation.NEVER)
	@Test
	public void selectOne(){
		TBL_Maintxdef main = new TBL_Maintxdef();
		main.setMuid("180007");
		
		TBL_Maintxdef ret = dao.selMaintxdefByIdx1(main);
		
		LOGGER.debug(ret.getMuname());
		
	}
	
	

}
