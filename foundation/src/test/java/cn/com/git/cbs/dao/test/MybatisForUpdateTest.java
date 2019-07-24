package cn.com.git.cbs.dao.test;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.datamodel.Cif;
import cn.com.git.cbs.platform.test.BaseTest;

/**
 * 测试forupdate查询几条数据，然后修改其中一条，最后commit
 * @author xia
 *
 */
public class MybatisForUpdateTest extends BaseTest {
		
	@Autowired	
	private GenericDAO<Cif> dao;
	
//	@Test
//	@Commit()
	public void selectForUpdate(){        
	    Cif test1 = new Cif();
//	    test1.setId(new BigDecimal(5));
	    test1.setCdlx("1");
		dao.selectForUpdate("",test1);
	}

}
