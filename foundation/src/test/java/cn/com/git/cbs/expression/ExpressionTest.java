package cn.com.git.cbs.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Test;
import cn.com.git.cbs.platform.test.BaseTest;


/**
 * 主交易 TRANFLOW表 ,测试交易代码 MUID='180067'
 * 测试各种条件语句的解析
 * @author xia
 *
 */

public class ExpressionTest extends BaseTest {

	@Test
    public void main()
    {
//		testStrSubstitutor();
//		testCase();
//		testCaseInsensitiveStrLookup();
//		testLength();
  	  
    	      
    	
    }

    
	/**
	 * 它使用${}的方法在形成了一个可配置的模板String。首先可以用一个Map声明一个 StrSubstitutor，然后使用replace方法，把模板String中使用${}的部分（内部为Map的key)，
	 * 转化为Map中的值，由此 做到动态更改字符串内容的效果
	 */
	public static void testStrSubstitutor() {
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put("amount", "500");
		valuesMap.put("target", "lazy dog");
		String templateString = "The ${amount} jumped over the ${target}.";
//		String templateString = "The &{animal} jumped over the &{target}.";
		
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
//		StrSubstitutor sub = new StrSubstitutor(valuesMap, "&(", ")");
		String resolvedString = sub.replace(templateString);
		System.out.println("the str="+resolvedString);		    
	}
    
	/**
	 * 使用treemap可以对key进行忽略大小写，但是我们使用hashmap
	 */
	public static void testCase() {
	       Map<String, String> valuesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);	
	       valuesMap.put("animal", "quick brown fox");
			valuesMap.put("target", "lazy dog");
			String templateString = "The ${Animal} jumped over the ${target}.";
//			String templateString = "The &{animal} jumped over the &{target}.";
			
			StrSubstitutor sub = new StrSubstitutor(valuesMap);
//			StrSubstitutor sub = new StrSubstitutor(valuesMap, "&(", ")");
			String resolvedString = sub.replace(templateString);
			System.out.println("the str="+resolvedString);	
	}
	
	/**
	 * 使用CaseInsensitiveStrLookup进行key的大小写忽略
	 */
	public static void testCaseInsensitiveStrLookup() {
		 Map<String, Object> messageValues = new HashMap<String, Object>();
		    messageValues.put("amount", "500");
		    messageValues.put("target", "李四");
//		    StrSubstitutor sub = new StrSubstitutor(new CaseInsensitiveStrLookup<String>(messageValues), "&(", ")", '\\');
		    StrSubstitutor sub = new StrSubstitutor(new CaseInsensitiveStrLookup(messageValues), "#(", ")", '\\');

//		    String format2 = sub.replace("Information: &(killer) killed &(target)!");
		    String format2 = sub.replace("Information: #(amount) killed #(target)!");
		    System.out.println("the str="+format2);	
//		    String format = sub.replace("Information: &(KILLER) killed &(TARGET)!");
		    String format = sub.replace("Information: #(KILLEr) killed #(TARGET)!");
		    System.out.println("the str="+format);	
//		    Assert.assertEquals(format, "Information: Johnson killed Quagmire!");
//		    Assert.assertEquals(format2, "Information: Johnson killed Quagmire!");
		    
		    //测试负数
		    messageValues.put("amount2", "500");
		    String format3 = sub.replace("Information: -#(amount2) ");
		    System.out.println("the 负数 str="+format3);
	}
	
//	/**
//	 * 测试Length方法
//	 * springel这个不方便，一个表达式，如#strlen('#(amounT)') ==1这样的，
//	 * 必须要第一步替换掉里面的值，
//	 * 第二步拆开，解析strlen函数，
//	 * 第三步才能解析表达式
//	 */
//	public static void testLength() {
//			Map<String, String> messageValues = new HashMap<String, String>();
//			messageValues.put("amount", "张三哇哈哈哈");
//		    StrSubstitutor sub = new StrSubstitutor(new CaseInsensitiveStrLookup<String>(messageValues), "#(", ")", '\\');
//
//		    String format2 = sub.replace("#strlen('#(amounT)') ==1");
//		    System.out.println("the str="+format2);	
//		    
//		ExpressionParser parser = new SpelExpressionParser();
//		StandardEvaluationContext context = new StandardEvaluationContext();
//				
//	
//		Method strlen = null;
//		try {
//			strlen = ExpressionUtil.class.getDeclaredMethod("strlen", String.class);
//		} catch (NoSuchMethodException e) {
//			LOGGER.error(e);
//		} catch (SecurityException e) {
//			LOGGER.error(e);
//		}
//		context.registerFunction("strlen", strlen);
////		String expression1 = "#strlen('张三')";
//		int result =
//		    parser.parseExpression(format2).getValue(context, Integer.class);
////		result=true
//		System.out.println("result="+result);
//		
//	}
	
	
	
	
	
	
	
	
	
	
}
