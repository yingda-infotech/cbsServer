package cn.com.git.cbs.expression;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.com.git.cbs.platform.test.BaseTest;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.common.FelBuilder;
import com.greenpineyu.fel.common.ObjectUtils;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.function.CommonFunction;
import com.greenpineyu.fel.function.Function;

public class FelEngineUtilTest  extends BaseTest {
	
	/**
	 * 计算测试
	 */
	@Test
	public void felTest(){
//		FelEngine fel = new FelEngineImpl();    
//		Object result = fel.eval("1234567890.12*123");    
//		System.out.println(result);   
		
		FelEngine fel = FelBuilder.bigNumberEngine();
//		  String input = "1234567890.123*98765";
//		 String input = "1234567890123456.123/365";
//		String input = "1234567890123456.123 +　999888777666";
		String input = "12345678901234567890.123 -　999888777666";
//		String input = "0.01 /　987654";
		
		 //计算结果，比如只要小数点后2位，3位，四舍五入的问题(不处理，调用者自己处理)
		 //结果不能要科学计数法		 
		 
		  Object value = fel.eval(input);
		  Object compileValue = fel.compile(input, fel.getContext()).eval(fel.getContext());
		  System.out.println("大数值计算（解释执行）:" + value);
		  System.out.println("大数值计算（编译执行）:" + compileValue);
		  
//		  BigDecimal big = new BigDecimal(value);
//		  System.out.println("big:" + big);
		  	
	}
	
//	@Test
	public void variableTest(){
		FelEngine fel = new FelEngineImpl(); 
		FelContext ctx = fel.getContext();    
		ctx.set("单价", 5000);    
		ctx.set("数量", 12);    
		ctx.set("运费", 7500);    
		Object result = fel.eval("单价*数量+运费");    
		System.out.println(result);   
	}
	
//	@Test
	public void functionTest(){
		  //定义hello函数   
	    Function hellofun = new CommonFunction() {   
	  
	        public String getName() {   
	            return "hello";   
	        }   
	           
	        /*   
	         * 调用hello("xxx")时执行的代码  
	         */   
	        @Override   
	        public Object call(Object[] arguments) {   
	            Object msg = null;   
	            if(arguments!= null && arguments.length>0){   
	                msg = arguments[0];   
	            }   
	            return ObjectUtils.toString(msg);   
	        }   
	  
	    };  
	    
	    Function hellofun2 = new CommonFunction() {   
	  	  
	        public String getName() {   
	            return "hello2";   
	        }   
	           
	        /*   
	         * 调用hello("xxx")时执行的代码  
	         */   
	        @Override   
	        public Object call(Object[] arguments) {   
	            Object msg = null;   
	            if(arguments!= null && arguments.length>0){   
	                msg = arguments[0];   
	            }   
	            return ObjectUtils.toString(msg);   
	        }   
	  
	    };  
	    
	    
	    
	    FelEngine e = new FelEngineImpl();   
	    //添加函数到引擎中。   
	    e.addFun(hellofun);   
	    e.addFun(hellofun2);   
	    String exp = "hello('张三') + hello2('李四')";   
	    //解释执行   
	    Object eval = e.eval(exp);   
	    System.out.println("hello "+eval);   
	    //编译执行   
//	    Expression compile = e.compile(exp, null);   
//	    eval = compile.eval(null);   
//	    System.out.println("hello "+eval);  
	}	
	
	@Test
	public void evalStringTest(){
		Map<String, Object> messageValues = new HashMap<String, Object>();

		messageValues.put("key1", "300");
		messageValues.put("key2", "aaa");
		messageValues.put("key3", "400");		
		
		FelEngineUtil.evalString("#{key1} + #{key2}", messageValues);
	}
	
	@Test
	public void evalBooleanTest(){
		Map<String, Object> messageValues = new HashMap<String, Object>();

		messageValues.put("key1", "300.55");
		messageValues.put("key2", "300.00");
		messageValues.put("key3", "400");		
		
		FelEngineUtil.evalBoolean("#{key1} == #{key2}", messageValues);
	}
	
	@Test
	public void evalBigDecimalTest(){
		Map<String, Object> messageValues = new HashMap<String, Object>();

		messageValues.put("key1", "300.555");		
		FelEngineUtil.evalBigDecimal("#{key1} + 100.00", messageValues);
	}
	
	@Test
	public void substrfunTest(){
		Map<String, Object> messageValues = new HashMap<String, Object>();

		messageValues.put("amount", "张三哇哈哈哈");
		messageValues.put("key", "100");
		messageValues.put("key1", "200");
		messageValues.put("key2", "300");
		messageValues.put("key3", "400");
		messageValues.put("Amt", "500");
		messageValues.put("Amt1", "600");
		messageValues.put("amount", "张三李四王五");
		messageValues.put("b.name2", "333");
//		messageValues.put("amount", "1234567890.321654");
		messageValues.put("amount", "12312312.855");
		
//		String str = "strlen(#{key1})==3 and 1==1 AND 1==0";	
//		String str = "substr('test',0,4)";			
//		String str = "choose(#{A1.name1},#{B.name2},#{C.name3})";
//		String str = "add(#{key1},#{key2},#{key3})";			
//		String str = "toInt(#{amount})";
//		String str = "atoi(#{amount})";
//		String str = "toNum(#{amount})";
//		String str = "toDouble(#{amount})";
		String str = "date_f('1','2017-02-07',30)";
//		String str = "date_f('2','2017-02-07',30)";
//		String str = "dateDiff('2017-02-07','2017-02-17')";
//		String str = "strstr('2017-2-7','222')";
//		String str = "mod(22,3)";
		
		FelEngineUtil.evalString(str, messageValues);
	}
	

}
