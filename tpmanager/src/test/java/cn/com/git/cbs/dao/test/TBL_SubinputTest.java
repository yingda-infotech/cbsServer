package cn.com.git.cbs.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.cbs.engine.dao.TBL_SubinputDao;
import cn.com.git.cbs.engine.datamodel.TBL_Subinput;
import cn.com.git.cbs.platform.test.BaseTest;

/**
 * 测试联合主键，不输入全部主键，然后会查询出多条还是一条数据
 * @author xia
 *
 */

public class TBL_SubinputTest extends BaseTest {
		
	@Autowired	
	private TBL_SubinputDao dao;
	
//	@Test
//	@Commit()
//	public void insert(){ 		
//		TBL_Subinput subinput = new TBL_Subinput();
//		
//		subinput.setMuid("110028");
//		subinput.setWumark("01");
//		subinput.setLocalname("wwwwwwwww");
//		
//		subinput.setPuid("12");  //一会查询看看是否会带4个空格
//		subinput.setMapmode("1");
//		subinput.setDataarea("1");
//		subinput.setPoolname("123");
//		subinput.setPoolwumark("12");
//		subinput.setFixvalue("123");
//		subinput.setDatadicname("12");
//		    
//		int ret = dao.insSubinput(subinput);
//		System.out.println(ret);
//	}
	
	@Test
	public void selectByPrimaryKey(){        
		TBL_Subinput subinput = new TBL_Subinput();
		
		subinput.setMuid("110028");
		subinput.setWumark("01");
		subinput.setLocalname("Passwd   ");
			    
		List<TBL_Subinput> ret = dao.selSubinputListByIdx1(subinput);
//		ReturnObj<TBL_Subinput> ret = dao.deleteByPrimaryKey(subinput);
		
		System.out.println(ret.size());
		
	
	}
	

}
