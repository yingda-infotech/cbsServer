//package cn.com.git.cbs.bigData.test;
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
//import cn.com.git.cbs.mybatis.model.Test2;
//import cn.com.git.cbs.platform.test.BaseTest;
//
///**
// * 测试的入口类测试mybatis
// */
//public class MybatisTest extends BaseTest {
//	@Autowired
//	private Test2Service service;
//
//	private static final Logger LOGGER = LoggerFactory
//			.getLogger(MybatisTest.class);
//
//	@Test
//	public void main() {
//
//		// testSelectByPrimaryKey(service);
//
//		testSelectByBetweenPK(service);
//
//	}
//
//	/**
//	 * 查询随机的一条数据，查询一万次 ，平均耗时67毫秒
//	 * 
//	 * @param service
//	 */
//	public void testSelectByPrimaryKey(Test2Service service) {
//		// 查询
//		long start = System.currentTimeMillis();
//
//		// for(int i=0;i<10000;i++){
//		for (int i = 0; i < 10; i++) {
//			int id = (int) (Math.random() * 9000000);
//			// LOGGER.info(Integer.toString(id));
//			service.selectByPrimaryKey(new BigDecimal(id));
//			// Test2 test2 = service.selectByPrimaryKey(new BigDecimal(id));
//			// if(test2 == null){
//			// LOGGER.info("null,    id:" +id);
//			// }else{
//			// LOGGER.info("查询结果:test2.getmuid:" + test2.getMuid() + ",    id:"
//			// +id);
//			// }
//
//		}
//
//		long end = System.currentTimeMillis();
//		LOGGER.info("查询10000次数据耗时:" + (end - start));
//
//	}
//
//	public void testSelectByBetweenPK(Test2Service service) { 
//		// 查询
//		long start = System.currentTimeMillis();
//
//		// for (int i = 0; i < 1000; i++) {
//		for (int i = 0; i < 3; i++) {
//			int id = (int) (Math.random() * 9000000);
//			Map<String, Object> paraMap = new HashMap<String, Object>();
//			paraMap.put("id1", new BigDecimal(id));
//			paraMap.put("id2", new BigDecimal(id + 99));
//			List<Test2> list = service.selectByBetweenPK(paraMap);
//			// LOGGER.info("长度:" + list.size());
//		}
//		long end = System.currentTimeMillis();
//		LOGGER.info("查询1000次数据耗时:" + (end - start));
//
//	}
//
//}
