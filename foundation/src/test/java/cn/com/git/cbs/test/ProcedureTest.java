//package cn.com.git.cbs.test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import cn.com.git.cbs.platform.test.BaseTest;
//import cn.com.git.cbs.service.Test2Service;
//
///**
// * 测试存储过程
// * @author xia
// *
// */
//public class ProcedureTest extends BaseTest{
//	private static final Logger LOGGER = LoggerFactory.getLogger(ProcedureTest.class);
//	@Autowired
//	private Test2Service service;
//    public void main()
//    {
////  		testCallTest_hello(service);
////  		testSelectByPrimaryKey(service);
////  		  testTlboxchginfo_update(service);
//  		testTlboxchginfo_delete(service);
//    }
//	
//	public void testCallTest_hello(Test2Service service){
//        
//        try {  
//              Map<String, String> param = new HashMap<String, String>();  
//              param.put("p_user_name", "zhangsan");  
//             service.callTest_hello(param);  
//              
//              LOGGER.info("message=" + param.get("p_user_name"));  
//              LOGGER.info("result=" + param.get("p_result"));  
//        } catch (Exception e) {  
//             LOGGER.error(e);  
//        } finally {   
//       }  
//    }  
//	
//	/**
//	 *  测试插入的存储过程
//	 * @param service
//	 */
//	public void testSelectByPrimaryKey(Test2Service service){
//		         
//          try {          	  
//              Map<String, Object> param = new HashMap<String, Object>();  
//              param.put("chgdate", "2018-03-30"); 
//              param.put("serseqno", "272"); 
//              param.put("N_brc", "101071"); 
//              param.put("N_newserseqno", "275"); 
//              param.put("N_newtrandate", "2016-03-28"); 
//              param.put("N_inserno", "101999"); 
//              String returnValue = service.tlboxchginfo_insert(param);  
//              
//              LOGGER.info("N_outserno=" + (Integer)param.get("N_outserno"));  
//              LOGGER.info("returnValue=" + returnValue);    //核心的存储过程不会返回值，所以是Null
//  
//          } catch (Exception e) {  
//               LOGGER.error(e);  
//          } finally {   
//         }  
//      }  
//	
//	/**
//	 *  测试修改的存储过程
//	 * @param service
//	 */
//	public void testTlboxchginfo_update(Test2Service service){
//		         
//          try {          	  
//              Map<String, Object> param = new HashMap<String, Object>();  
//              param.put("Ochgdate", "2016-03-28"); 
//              param.put("Nchgdate", "272"); 
//              param.put("Oserseqno", "105"); 
//              param.put("Nserseqno", "275"); 
//              param.put("brc", "101000"); 
//              param.put("newserseqno", "101999"); 
//              param.put("newtrandate", "2016-03-28"); 
//              param.put("inserno", "101999"); 
//              
//              String returnValue = service.tlboxchginfo_update(param);  
//              
//              LOGGER.info("outserno=" + (Integer)param.get("outserno"));  
//              LOGGER.info("returnValue=" + returnValue); 
//  
//          } catch (Exception e) {  
//               LOGGER.error(e);  
//          } finally {   
//         }  
//      }
//	
//	/**
//	 *  测试删除的存储过程
//	 * @param service
//	 */
//	public void testTlboxchginfo_delete(Test2Service service){
//		         
//          try {          	  
//              Map<String, Object> param = new HashMap<String, Object>();  
//              param.put("Ochgdate", "2016-03-28"); 
//              param.put("Oserseqno", "105"); 
//              param.put("brc", "105"); 
//              param.put("newserseqno", "105"); 
//              param.put("newtrandate", "2016-03-28"); 
//              param.put("inserno", "101999"); 
//              
////              String returnValue = service.tlboxchginfo_delete(param);  
//              
////              LOGGER.info("outserno=" + (Integer)param.get("outserno"));  
////              LOGGER.info("returnValue=" + returnValue); 
//  
//          } catch (Exception e) {  
//               LOGGER.error(e);  
//          } finally {   
//         }  
//      }
//		
//	}
//
