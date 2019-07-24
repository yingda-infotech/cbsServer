package cn.com.git.cbs.expression;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.com.git.cbs.expression.JsExpressionUtil;
import cn.com.git.cbs.platform.test.BaseTest;

public class JsExpressionUtilTest  extends BaseTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsExpressionUtilTest.class);
	
//	@Test
//	@Transactional(propagation=Propagation.NEVER)
	public void checkLogic(){
		
		Map<String, Object> messageValues = new HashMap<String, Object>();
		messageValues.put("amount", "张三哇哈哈哈");
		messageValues.put("key", "100");
		messageValues.put("key1", "200");
		messageValues.put("key2", "300");
		messageValues.put("key3", "400");
		messageValues.put("Amt", "500");
		messageValues.put("Amt1", "600");
		messageValues.put("amount", "张三李四王五");
		
		String str = "strlen(#{key1})==3 and 1==1 AND 1==0";
		
		boolean obj = JsExpressionUtil.evalBoolean(str,messageValues);
 		LOGGER.info(String.valueOf(obj));

	}
	
	@Test
	public void checkRunEval(){
		
		Map<String, Object> messageValues = new HashMap<String, Object>();
		messageValues.put("amount", "张三哇哈哈哈");
		messageValues.put("key", "100");
		messageValues.put("key1", "200");
		messageValues.put("key2", "300");
		messageValues.put("key3", "400");
		messageValues.put("Amt", "500");
		messageValues.put("Amt1", "600");
		messageValues.put("amount", "张三李四王五");
		
		messageValues.put("big1", "123456789.12");
		messageValues.put("big2", "987654321.09");
		
		String str = "( #{key1} + 100.00 ) * #{key2} / #{key3} - 200.00";
		String str2 = "100/Infinity";
				
 		LOGGER.info(JsExpressionUtil.evalString(str,messageValues));
 		LOGGER.info(JsExpressionUtil.evalString(str2, messageValues));
 		
 		//大金额计算
 		String big = "#{big1} * #{big2}";
// 		String big = "#big1 * #big2";
 		LOGGER.info(JsExpressionUtil.evalString(big,messageValues));
	}
	
//	@Test
	public void toInt(){
		Map<String, Object> messageValues = new HashMap<String, Object>();
//		messageValues.put("amount", "256");
//		messageValues.put("amount", "哇哈哈");
		messageValues.put("amount", "1234567890.321654");
		String str = "toInt(#{amount})";
		
		int obj = JsExpressionUtil.evalInt(str,messageValues);
		LOGGER.info("toInt():" + obj);
	}
	
//	@Test
	public void toDouble(){
		Map<String, Object> messageValues = new HashMap<String, Object>();
//		messageValues.put("amount", "256");
//		messageValues.put("amount", "哇哈哈");
//		messageValues.put("amount", "1234567890.321654");
		messageValues.put("amount", "1234567.3126");
		String str = "toDouble(#{amount})";
		
		double obj = JsExpressionUtil.evalDouble(str,messageValues);
		LOGGER.info("toDouble:" + obj);
	}
	
//	@Test
	public void choose(){
		Map<String, Object> messageValues = new HashMap<String, Object>();
		messageValues.put("b.name2", "333");
		String str = "choose(#{A1.name1},#{B.name2},#{C.name3})";
		
		String obj = JsExpressionUtil.evalString(str,messageValues);
		LOGGER.info("choose:" + obj);
	}
	
	//三目运算符
	@Test
	public void three(){
		Map<String, Object> messageValues = new HashMap<String, Object>();
		messageValues.put("flag", "1");
		messageValues.put("a", "现金");
		String str = "((#{Flag} == 0)) ? (#{A} == '现金') : (#{A} == '转账')";
		
		String obj = JsExpressionUtil.evalString(str,messageValues);
		LOGGER.info("three:" + obj);
	}
	
//	@Test
	public void express(){
		Map<String, String> messageValues = new HashMap<String, String>();
		messageValues.put("key1", "100");
		messageValues.put("key2", "22");
		messageValues.put("key3", "aaa");
		messageValues.put("key4", "200");
		messageValues.put("date1", "2016-12-11");
		messageValues.put("date2", "2016-12-09");
		messageValues.put("str1", "abcefg");
		messageValues.put("str2", "f");
		
		//测试字符串拼接
//		String str = "#{key1}+#{key2}";
//		LOGGER.info(str + ":" + JsExpressionUtil.evalString(str,messageValues));
//		
//		String str2 = "#{key3}+#{key4}";		
// 		LOGGER.info(str2 + ":" + JsExpressionUtil.evalString(str2, messageValues));
// 		
// 		//测试atoi  字符串转整数 		
// 		String str3 = "atoi(#{key3})";		
// 		LOGGER.info(str3 + ":" + JsExpressionUtil.evalInt(str3, messageValues));
// 		
// 		String str4 = "atoi(#{key4})";		
// 		LOGGER.info(str4 + ":" + JsExpressionUtil.evalInt(str4, messageValues));
 		
// 		//测试 dateDiff (p2 , p3 )
// 		String str5 = "dateDiff(#{date1},#{date2})";
//		LOGGER.info(str5 + ":" + JsExpressionUtil.evalInt(str5,messageValues));
		
		//测试date_f
// 		String str6 = "date_f(1,#{date1},30)";
//		LOGGER.info(str6 + ":" + JsExpressionUtil.evalString(str6,messageValues));
//		
// 		String str7 = "date_f(2,#{date2},30)";
//		LOGGER.info(str7 + ":" + JsExpressionUtil.evalString(str7,messageValues));
//		
// 		String str8 = "date_f(4,#{date2},30)";
//		LOGGER.info(str8 + ":" + JsExpressionUtil.evalString(str8,messageValues));
		
		//测试判断字符串包含
// 		String str9 = "strstr(#{str1},#{str2})";
//		LOGGER.info(str9 + ":" + JsExpressionUtil.evalString(str9,messageValues));
		
		//整数取模
//		String str10 = "mod(#{key1},#{key9})";
//		LOGGER.info(str10 + ":" + JsExpressionUtil.evalInt(str10,messageValues));		
		
	}
	
}
