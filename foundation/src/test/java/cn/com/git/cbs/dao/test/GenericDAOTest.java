package cn.com.git.cbs.dao.test;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.datamodel.Cif;
import cn.com.git.cbs.platform.test.BaseTest;

/**
 * 测试Mybatis的公共方法
 * @author xia
 *
 */
@Rollback(true)
public class GenericDAOTest extends BaseTest {

	@Autowired	
	private GenericDAO<Cif> dao;
		    
//	@Test
//	@Commit()
	public void insert(){ 		
		 Cif cif = new Cif();
		    cif.setId(new BigDecimal(4));
		    cif.setOprtime("1");
		    cif.setCdlx("1");
		    
		int ret = dao.insert("",cif);
		System.out.println(ret);
	}
	
//	@Test
//	@Commit()
//	public void insertSelective(){ 		
//		 Cif cif = new Cif();
//		    cif.setId(new BigDecimal(6));
//		    cif.setOprtime("1");
//		    cif.setCdlx("1");
//		    
//		int ret = dao.insertSelective("",cif);
//		System.out.println(ret);
//	}
	
//	@Test
//	@Commit()
	public void updateByPrimaryKey(){ 		
		 Cif cif = new Cif();
		    cif.setId(new BigDecimal(6));
		    cif.setOprtime("3");
		    cif.setCdlx("6");
		    int ret = dao.updateByPrimaryKey("",cif);
		System.out.println(ret);
	}
	
//	@Test
//	@Commit()
//	public void updateByPrimaryKeySelective(){ 		
//		 Cif cif = new Cif();
//		    cif.setId(new BigDecimal(2));
////		    cif.setOprtime("3");
//		    cif.setCdlx("5");
//		    
//		int ret = dao.updateByPrimaryKeySelective("",cif);
//		System.out.println(ret);
//	}
	
//	@Test
//	@Commit()
	public void deleteByPrimaryKey(){ 		
		 Cif cif = new Cif();
		    cif.setId(new BigDecimal(3));
		    
		int ret = dao.deleteByPrimaryKey("",cif);
		System.out.println(ret);
	}
	
	
//	@Test
//	public void selectByPrimaryKey(){        
//	    Cif test1 = new Cif();
//	    test1.setId(new BigDecimal(5));
//		ReturnObj<Cif> ret = dao.selectByPrimaryKey(test1);
//		if(ret.getRetValue() == ReturnObj.SUCCESSFUL){
//			System.out.println(ret + "-------" + ret.getData().getId() + "," + ret.getData().getOprtime() + ","  + ret.getData().getCdlx());	
//		}else{
//			System.out.println(ret.getRetValue());
//		}
//	}
	
//	@Test
//	@Commit()
//	public void selectByPrimaryKeyForUpdate(){        
//	    Cif test1 = new Cif();
//	    test1.setId(new BigDecimal(5));
//		ReturnObj<Cif> ret = dao.selectByPrimaryKeyForUpdate(test1);
//		if(ret.getRetValue() == ReturnObj.SUCCESSFUL){
//			System.out.println(ret + "-------" + ret.getData().getId() + "," + ret.getData().getOprtime() + ","  + ret.getData().getCdlx());	
//		}else{
//			System.out.println(ret.getRetValue());
//		}
//	}
	
//	@Test
//	public void select(){		
//        Cif test1 = new Cif();
//        test1.setCdlx("1");
//        
//        ReturnObj<List<Cif>> ret = dao.select(test1);
//		if(ret.getRetValue() == ReturnObj.SUCCESSFUL){
//			System.out.println(ret + "-------查询出来的结果条数为" + ret.getData().size());	
//			for(int i=0;i<ret.getData().size();i++){
//				Cif testtmp = ret.getData().get(i);
//				System.out.println(testtmp.getId() + "," + testtmp.getOprtime() + ","  + testtmp.getCdlx());
//			}
//		}else{
//			System.out.println(ret);
//		}
//	}
	
	
	

}
