//package cn.com.git.cbs.bigData.test;
//
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import cn.com.git.cbs.dbapi.daoimpl.Test2ImplDao;
//import cn.com.git.cbs.mybatis.model.Test2;
//import cn.com.git.cbs.platform.test.BaseTest;
//
///**
// * 测试的入口类测试dbapi
// */
//public class DbapiTest extends BaseTest
//{	
//	@Autowired
//	private Test2ImplDao service;
//	private static final Logger LOGGER = LoggerFactory.getLogger(DbapiTest.class);
//	
//	@Test
//    public void main()
//    {
//             	  
//    	
//    }
//	
//	
//	/**
//	 * 查询随机的一条数据，查询一万次 ，平均耗时67毫秒  
//	 * @param service
//	 */
//	public void testSelectByPrimaryKey(Test2ImplDao service){
//        //查询    	
//	  long start = System.currentTimeMillis();
//		  
//	  for(int i=0;i<10000;i++){
//		 int id = (int) (Math.random() * 9000000);
//		  Test2 test2 = new Test2();
//	  	   test2.setId(new BigDecimal(id));
//	  	   int ret = service.selectByPrimaryKey(test2);
////	  	   LOGGER.info("查询结果:" + ret+",id" +id);  
//	  }
//	  
//	  	long end = System.currentTimeMillis();
//	  	LOGGER.info("查询10000次数据耗时:" + (end - start));  
//		
//	}
//	
//	/**
//	 * 动态拼sql方式，每次拼100条  BETWEEN 
//	 * @param service
//	 */
//	public void testSelectBySql(Test2ImplDao service){
//		 long start = System.currentTimeMillis();
//    	//查询随机的一条数据和他之上的100条数据
//	  	  for(int i=0;i<1000;i++){
//	  		 StringBuffer sb = new StringBuffer();
//	  		 sb.append("SELECT * FROM ETELLER.TEST2 where id BETWEEN ");
//	 		  int id = (int) (Math.random() * 9000000); 		  
//	  		  sb.append(Integer.toString(id));
//	  		  sb.append(" and ");
//	  		 sb.append(Integer.toString(id + 99));			//查询区间100
//	  	  	  List list = service.selectBySql(sb.toString());
////	  	  	  LOGGER.info("查询结果list长度 :" + list.size() + ";id:" + id);  
//	  	  }
//  	   
//    	long end = System.currentTimeMillis();
//    	LOGGER.info("查询1000次数据耗时:" + (end - start));  
//	}
//	
//	/**
//	 * 命名sql方式，每次拼100条  BETWEEN 
//	 * @param service
//	 */
//	public void testSelectByNamedPara(Test2ImplDao service){
//    	//查询随机的一条数据和他之上的100条数据
//		 long start = System.currentTimeMillis();
//	  	  for(int i=0;i<1000;i++){
//	  		 StringBuffer sb = new StringBuffer();
//	  		 sb.append("SELECT * FROM ETELLER.TEST2 where id BETWEEN ");
//	 		  int id = (int) (Math.random() * 9000000);
//	 		 sb.append(":id1");
//	 		  sb.append(" and :id2");
//	 		 Map<String, Object> paraMap = new HashMap();
//	 		paraMap.put("id1", Integer.toString(id));
//	 		paraMap.put("id2", Integer.toString(id + 99));
//	  	  	  List list = service.selectByNamedPara(sb.toString(),paraMap);
//	  	  	   LOGGER.info("查询结果list长度 :" + list.size() + ";id:" + id);  
//	  	  }  	   
//    	long end = System.currentTimeMillis();
//    	LOGGER.info("查询1000次数据耗时:" + (end - start));  
//	}
//	
//	
//	
//	
//}