package cn.com.git.cbs.expression;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import cn.com.git.cbs.log.PlatformLogger;

/**
 * 测试调用js表达式
 * @author xia
 *
 */

public class JsTest {

	private static final PlatformLogger LOGGER = PlatformLogger.create();
	
	@Test
    public void main() throws ScriptException, NoSuchMethodException, IOException
    {
//		testJs();
		
//		try {
//			testFileJs();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			LOGGER.error(e);
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			LOGGER.error(e);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			LOGGER.error(e);
//		}
		
		Map<String, Object> messageValues = new HashMap<String, Object>();
		messageValues.put("amount", "张三哇哈哈哈");
		messageValues.put("key", "100");
		messageValues.put("key1", "100");
		messageValues.put("key2", "300");
		messageValues.put("key3", "400");
		messageValues.put("Amt", "500");
		messageValues.put("Amt1", "600");
		messageValues.put("big1", "600");
		messageValues.put("big2", "600");
		messageValues.put("amount", "张三李四王五");
		//使用#{)来获取参数
//	    StrSubstitutor sub = new StrSubstitutor(new CaseInsensitiveStrLookup<String>(messageValues), "#{", ")", '\\');
		//改用#{}来获取参数
		StrSubstitutor sub = new StrSubstitutor(new CaseInsensitiveStrLookup(messageValues), "#{", "}", '\\');
			    
		//加载js函数
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript");  
//		String jsFileName = "src/main/resources/META-INF/expression.js";   // 读取js文件		
		
		String jsFileName = "/META-INF/expression.js";   // 读取js文件   
		//FileReader reader = new FileReader(new ClassPathResource(jsFileName).getFile());   // 执行指定脚本
		Reader reader=new InputStreamReader(new ClassPathResource(jsFileName).getInputStream());
		engine.eval(reader);   

//		testStrlen(sub,engine);
//		testSub(sub,engine);
//		testCalculate(sub,engine);
		runToCompare(sub,engine);
//		runToLogic(sub,engine);
//		runToChoose(sub,engine);
//		checkBigDecimal(sub,engine);
		
		reader.close();  

    }
	
	/**
	 *测试调用js
	 */
	public static void testJs(){
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		Object result = null;
		
			try {
				result = engine
						.eval("function strlen(x) {return x.length()} strlen(\"333\")==3 && 1==2");
			} catch (ScriptException e) {
				LOGGER.error(e);
			}
			 System.out.println(result);	
	}

	/**
	 *测试调用文件js
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 * @throws IOException 
	 */
	public static void testFileJs() throws ScriptException, NoSuchMethodException, IOException{
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript");     

		String jsFileName = "/META-INF/expression.js";   // 读取js文件   
		//FileReader reader = new FileReader(new ClassPathResource(jsFileName).getFile());   // 执行指定脚本
		Reader reader=new InputStreamReader(new ClassPathResource(jsFileName).getInputStream());
		engine.eval(reader);   

		if(engine instanceof Invocable) {    
			Invocable invoke = (Invocable)engine;    // 调用merge方法，并传入两个参数    
	
			// c = merge(2, 3);    
			Double c = (Double)invoke.invokeFunction("merge", 3, 3);    
			System.out.println("c = " + c);   
		}   

		reader.close();  

	}
	
	/**
	 * 测试Strlen函数
	 * strlen('#{amounT}')
	 */
	public static void testStrlen(StrSubstitutor sub,ScriptEngine engine){
//		  String format = sub.replace("sub('#{amounT}',3,6)");
//		String format = sub.replace("strlen('#{key1}')");
		String format = sub.replace("strlen('#{key1}')==4");
		    System.out.println("the str="+format);	
		    
		    try {
		    	 System.out.println(engine.eval(format));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e);
			}
		    
	}
	
	/**
	 * 测试sub函数
	 * sub('#{amounT}',x,y)
	 */
	public static void testSub(StrSubstitutor sub,ScriptEngine engine){
		String format = sub.replace("substr('#{amounT}',3,6)");
	    System.out.println("the str="+format);	
	    
	    try {
	    	 System.out.println(engine.eval(format));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
	}
	
	/**
	 * 算术表达式
	 * @param sub
	 * @param engine
	 */
	public static void testCalculate(StrSubstitutor sub,ScriptEngine engine){
		String format = sub.replace("( #{key1} + 100.00 ) * #{key2} / #{key3} - 200.00");
	    System.out.println("the str="+format);	
	    
		String format2 = sub.replace("(( -#{Key1} + #{Key2} ) * #{Key3})");
	    System.out.println("the str2="+format2);	
	    
	    try {
	    	 System.out.println(engine.eval(format));
	    	 System.out.println(engine.eval(format2));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
	}
	
	
	/**
	 * 测试比较表达式
	 */
	public static void runToCompare(StrSubstitutor sub,ScriptEngine engine){	
		String format = sub.replace("tonum(#{Key1}) + 1 == 101 AND 1==0 ");
//		String format = sub.replace("choose(#{1111},'',#{key})");
	    System.out.println("the str="+format);	
	    
		String format2 = sub.replace("'2' == '2'");
	    System.out.println("the str2="+format2);	
	    
	    try {
	    	 System.out.println(engine.eval(format));
	    	 System.out.println(engine.eval(format2));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
	}
	
	/**
	 * 逻辑比较表达式
	 * 逻辑与的"&&"，逻辑或的"||"。逻辑运算符的操作数只能为Boolean。
	 * 不支持and 和 or
	 */
	public static void runToLogic(StrSubstitutor sub,ScriptEngine engine){	
		String format = sub.replace("((#{Key) > 10) && ((#{Key1) == 100.00) || (#{Key2) == 200.00) ))");
	    System.out.println("the str="+format);	
	    
		String format2 = sub.replace("(#{Key) + #{Key) > 100.00 || #{Key) < 10 ) && (#{Key2) != 20) ");
	    System.out.println("the str2="+format2);	
	    
		String format3 = sub.replace("((#{Key) > 10) && (#{Key1) != 100.00)) ? #{Key) : (#{Key1) + #{Key2)) ");
	    System.out.println("the str3="+format3);
	    
	    try {
	    	 System.out.println(engine.eval(format));
	    	 System.out.println(engine.eval(format2));
	    	 System.out.println(engine.eval(format3));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
	}
	
	/**
	 * 1、根据‘||’处理各表达式，直至取到值，如有未处理表达式，不再处理
		2、允许嵌套二元表达式、算术表达式
	 * @param sub
	 * @param engine
	 */
	public static void runToChoose(StrSubstitutor sub,ScriptEngine engine){	
		String format = sub.replace("choose(#{1111},'',#{key})");
	    System.out.println("the str="+format);	
	    
	    try {
	    	 System.out.println(engine.eval(format));
//	    	 System.out.println(engine.eval(format2));
//	    	 System.out.println(engine.eval(format3));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
	}
	
	/**
	 * 测试BigDecimal类型的计算，看看有没有溢出和精度问题
	 * @param str
	 */
	public static void checkBigDecimal(StrSubstitutor sub,ScriptEngine engine){
		String format = sub.replace("add(#{big1},#{big2})");
	    System.out.println("the str="+format);	
		    
		    try {
		    	 System.out.println(engine.eval(format));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e);
			}
	}
	
	
	
	

		
}
