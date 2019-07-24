//package cn.com.git.cbs.test;
//
//import java.math.BigDecimal;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
//import cn.com.git.cbs.dbapi.daoimpl.Test2ImplDao;
//import cn.com.git.cbs.exception.CbsRunException;
//import cn.com.git.cbs.mybatis.model.Test2;
//
////@RunWith(Parameterized.class) 
//public class InsertDataTest extends Thread {
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(InsertDataTest.class);
//	
//	private int beginNum;
//	
//	private ApplicationContext appContext;
//	  
//	 public int getBeginNum() {
//		return beginNum;
//	}
//
//	public void setBeginNum(int beginNum) {
//		this.beginNum = beginNum;
//	}
//	
//	private InsertDataTest(ApplicationContext appContext) {
//		this.appContext=appContext;
//	}
//
//	public void run() {
//		    
//			Test2ImplDao service = (Test2ImplDao) appContext.getBean("test2ImplDao");  
//			
//			  DataSourceTransactionManager tran = (DataSourceTransactionManager) appContext.getBean("transactionManager");   
//	        	//通过DefaultTransactionDefinition对象来持有事务处理属性 
//	        	  DefaultTransactionDefinition def = new DefaultTransactionDefinition();//事务定义类	        	  
//	        	  def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//	        	//获取事务的状态  
//	        	  TransactionStatus status = tran.getTransaction(def);//返回事务对象
//	        	  
//	        	  
//	        	  
////	        	  PlatformTransactionManager platformTransactionManager = tran.
////	        	  //重置事务状态。没这句话会报错。这就是问题四的解决方法  
////	               status=platformTransactionManager.getTransaction(def); 
//			
//			//测试dbpi
//	    	Test2 Test2 = new Test2();
////	    	for(int i = beginNum;i<(beginNum+100000);i++){    
//	    	
//
//	        	Test2.setId(new BigDecimal(1111111112));
//	        	Test2.setMuid(Integer.toString(beginNum));
//	        	Test2.setWorkseq(Integer.toString(beginNum));
//	        	Test2.setWuid("An exception translator for all exception thrown by the");
//	        	Test2.setWumark("An exception translator for all exception thrown by the");
//	        	Test2.setEntrycond("An exception translator for all exception thrown by the");
//	        	Test2.setEntryfunc("An exception translator for all exception thrown by the");
//	        	Test2.setCtrlmode("An exception translator for all exception thrown by the");
//	        	Test2.setCommitmode("An exception translator for all exception thrown by the");
//	        	Test2.setFlowmode("An exception translator for all exception thrown by the");
//	        	Test2.setPrewumark("An exception translator for all exception thrown by the");
//	        	Test2.setClearprdt("An exception translator for all exception thrown by the");
//	        	Test2.setFeetype("An exception translator for all exception thrown by the");
//	        	Test2.setAnalysecode("An exception translator for all exception thrown by the");
//	        	Test2.setClasscode1("An exception translator for all exception thrown by the");
//	        	Test2.setClasscode2("An exception translator for all exception thrown by the");
//	        	Test2.setClasscode3("An exception translator for all exception thrown by the");
//	        	Test2.setClasscode4("An exception translator for all exception thrown by the");
//	        	Test2.setParam1("An exception translator for all exception thrown by the");
//	        	Test2.setParam2("An exception translator for all exception thrown by the");
//	        	Test2.setParam3("An exception translator for all exception thrown by the");
//	        	Test2.setMemo("An exception translator for all exception thrown by the");
//	        	
//	        		        	
//	         	 try {
//	         		LOGGER.info("插入结果:" +  service.insertData(Test2));
////	         		if(i%50 == 0 && i>0){
//		        		 tran.commit(status);		
////		        		 status = tran.getTransaction(def);//返回事务对象
////		        	}
//	         	} catch (CbsRunException e) {
//	         		// TODO Auto-generated catch block
//	         		LOGGER.error(e.getMessage(),e);
//	         		tran.rollback(status);
//	         	} 
//	    	}
////	 }
//	
//	 public static void main(String[] args)
//      {
//		 ApplicationContext appContext = new ClassPathXmlApplicationContext("/META-INF/applicationContext.xml");  
////		 for(int i=100;i<101;i++){
//			 InsertDataTest insert = new InsertDataTest(appContext);
////			 insert.setBeginNum(i*100000);
//			 insert.start();
////		 }	 		
//      }
//
//}
