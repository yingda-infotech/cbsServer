package cn.com.git.cbs.expression;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import cn.com.git.cbs.log.PlatformLogger;



public class SpringELTest {
	
	private static final PlatformLogger LOGGER = PlatformLogger.create();

    public void main()
    {
//		runSPEL();	
//		runConcat();
//		runTemplate();
//		runParseExpression();
//		getValue();
//		registerFunction();
//		testObject();
//		testCollection();
		updateCollection();
    }

	public static void runSPEL() {
		ExpressionParser parser = new SpelExpressionParser();  
	    EvaluationContext context = new StandardEvaluationContext();
	    long begin=Calendar.getInstance().getTimeInMillis();
	    
		    context.setVariable("Amt", 2000); 
		    context.setVariable("_02_Acc", 100.0f);
//		    Double result1 = parser.parseExpression("#Amt*#_02_Acc").getValue(context, Double.class);  
		    
		    
	    String expression1 = "#Amt == 2000";
	    boolean result1 = parser.parseExpression(expression1).getValue(boolean.class);
	    
	    long end=Calendar.getInstance().getTimeInMillis();
	    System.out.println("result1："+result1);
	    System.out.println("Total time："+(end-begin));
	}
	
	/**
	 * concat替换节点信息
	 */
	public static void runConcat() {
		//构造上下文：准备比如变量定义等等表达式运行需要的上下文数据
		EvaluationContext context = new StandardEvaluationContext();
		//创建解析器：提供SpelExpressionParser默认实现
		ExpressionParser parser = new SpelExpressionParser();
		//解析表达式：使用ExpressionParser来解析表达式为相应的Expression对象
		org.springframework.expression.Expression expression =	parser.parseExpression("('Hello' + ' World').concat(#end)");
		 
		//设置上下文中的变量的值
		context.setVariable("end", "!SpringEL11111");
		//执行表达式，获取运行结果
		String str = (String)expression.getValue(context);
		System.out.println("the str="+str);
	}
	
	/**
	 * 自定义一个解析模板的规则
	 * Hello World!
	 */
	public static void runTemplate() {
		 ExpressionParser parser = new SpelExpressionParser();
		 //自定义一个解析模板的规则
		    ParserContext parserContext = new ParserContext() {
		        public boolean isTemplate() {             return true;        }
		        public String getExpressionPrefix() {     return "#{";        }
		        public String getExpressionSuffix() {     return "}";        }
		    };
		    String template = "#{'Hello '}#{'World!'}";
		    Expression expression = (Expression) parser.parseExpression(template, parserContext);
		    System.out.println( ((org.springframework.expression.Expression) expression).getValue());
	}
	
	/**
	 * 解析所有类型和运算符计算
	 */
	public static void runParseExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		
		String str1 = parser.parseExpression("'Hello World!'").getValue(String.class);
		String str2 = parser.parseExpression("\"Hello World!\"").getValue(String.class);
		int int1 = parser.parseExpression("1").getValue(Integer.class);
		float float1 = parser.parseExpression("1.1").getValue(Float.class);
		boolean true1 = parser.parseExpression("true").getValue(boolean.class);
		Object null1 = parser.parseExpression("null").getValue(Object.class);		
//		Hello World!
//		Hello World!
//		1
//		1.1
//		true
//		null		
		 System.out.println(str1);
		 System.out.println(str2);
		 System.out.println(int1);
		 System.out.println(float1);
		 System.out.println(true1);
		 System.out.println(null1);
		 
		 int result1 = parser.parseExpression("1+2-3*4/2").getValue(Integer.class);
		 int result2 = parser.parseExpression("4%3").getValue(Integer.class) ;
		 int result3 = parser.parseExpression("2^3").getValue(Integer.class);		 
//		 -3
//		 1
//		 8		 
		 System.out.println(result1);
		 System.out.println(result2);
		 System.out.println(result3);
		 
		 boolean bool1 = parser.parseExpression("1>2").getValue(boolean.class);
		 boolean bool2 = parser.parseExpression("1 between {1, 2}").getValue(boolean.class);
//		 false
//		 true
		 System.out.println(bool1);
		 System.out.println(bool2);
		 
		 String expression1 = "2>1 and (!true or !false)";
		 boolean bool3 = parser.parseExpression(expression1).getValue(boolean.class);
//		 true
		 System.out.println(bool3);
		 
	}
	
	/**
	 * 获取数据
	 */
	public static void getValue() {
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("variable", "hello1");
		context.setVariable("variable", "hello2");
		String result1 = parser.parseExpression("#variable").getValue(context, String.class);
		System.out.println("r1=="+result1);
//		 SpE还允许引用根对象及当前上下文对象，使用“#root”引用根对象，使用“#this”引用当前上下文对象；
		context = new StandardEvaluationContext(12);
		String result2 = parser.parseExpression("#root-1").getValue(context, String.class);
		System.out.println("r2=="+result2);
		String result3 = parser.parseExpression("#this").getValue(context, String.class);
		System.out.println("r3=="+result3);
//		r1==hello2
//		r2==11
//		r3==12
	}
	
	/**
	 * 测试自定义函数
	 * SpEL使用StandardEvaluationContext的registerFunction方法进行注册自定义函数，其实完全可以使用setVariable代替，两者其本质是一样的
	 * “registerFunction”和“setVariable”都可以注册自定义函数，但是两个方法的含义不一样，推荐使用“registerFunction”方法注册自定义函数。
	 */
	public static void registerFunction() {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		Method parseInt = null;
		try {
			parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
		context.registerFunction("regParseInt", parseInt);
		context.setVariable("parseInt2", parseInt);
		String expression1 = "#regParseInt('3') == #parseInt2('3')";
		boolean result =
		    parser.parseExpression(expression1).getValue(context, boolean.class);
//		result=true
		System.out.println("result="+result);
		
		
	}
	
	/**
	 * 对象属性获取非常简单，使用如“a.property.property”这种点缀式获取，SpEL对于属性名首字母是不区分大小写的；
		SpEL还引入了Groovy语言中的安全导航运算符“(对象|属性)?.属性”，用来避免当“?.”前边的表达式为null时抛出空指针异常，而是返回null；
		修改属性值可以通过赋值表达式或Expression接口的setValue方法修改。
	 */
//	public static void testObject() {
////		Cif um = new Cif();
////		um.setUuid("User1");
////		um.setName("UserName1");
//		um.setOprtime("User1");
//		um.setCdlx("UserName1");
//		ExpressionParser parser = new SpelExpressionParser();
//		StandardEvaluationContext context = new StandardEvaluationContext();
//		context.setVariable("um",um);
//		//取值
//		Expression expression = parser.parseExpression("'oprtime='+#um.oprtime + ',cdlx='+#um.cdlx");
//		String v =  expression.getValue(context,String.class);
//		System.out.println("v=="+v);
//		//赋值
//		expression = parser.parseExpression("'oprtime='+(#um.oprtime='newUser') + ',cdlx='+#um.cdlx");
//		v =  expression.getValue(context,String.class);
//		System.out.println("v2=="+v);
////		v==oprtime=User1,cdlx=UserName1
////		v2==oprtime=newUser,cdlx=UserName1
//		
//
//	}
	
	/**
	 * //SpEL目前支持所有集合类型的访问
	 */
	public static void testCollection() {
		ExpressionParser parser = new SpelExpressionParser();
//		内联List
//		String expression3 = "{{1+2,2+4},{3,4+4}}";   //[[3, 6], [3, 8]]
//		List result3 = parser.parseExpression(expression3).getValue(List.class);
//		((Calendar) result3.get(0)).set(0, 1);
		
		Collection<Integer> collection = new HashSet<Integer>();
		collection.add(1);
		collection.add(2);
		EvaluationContext context2 = new StandardEvaluationContext();
		context2.setVariable("collection", collection);
		int result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class);
		System.out.println("result2=="+result2);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "123");
		EvaluationContext context3 = new StandardEvaluationContext();
		context3.setVariable("map", map);
		int result3 = parser.parseExpression("#map['a']").getValue(context3, int.class);
		System.out.println("result3=="+result3);
		
	}
	
	/**
	 * 元素修改
	     可以使用赋值表达式或Expression接口的setValue方法修改
	 */
	public static void updateCollection() {
		ExpressionParser parser = new SpelExpressionParser();
//		1：//修改数组元素值
		int[] array = new int[] {1, 2};   //修改后[1, 3]
		EvaluationContext context1 = new StandardEvaluationContext();
		context1.setVariable("array", array);
		int result1 = parser.parseExpression("#array[1] = 3").getValue(context1, int.class);
		System.out.println("result1=="+result1);
		
		//修改集合值
		Collection<Integer> collection = new ArrayList<Integer>();
		collection.add(1);
		collection.add(2);
		EvaluationContext context2 = new StandardEvaluationContext();
		context2.setVariable("collection", collection);
		int result2 = parser.parseExpression("#collection[1] = 3").getValue(context2, int.class);
		System.out.println("result2=="+result2);
		parser.parseExpression("#collection[1]").setValue(context2, 4);
		result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class);
		System.out.println("result2=="+result2);
		
		//修改map元素值
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 1);
		EvaluationContext context3 = new StandardEvaluationContext();
		context3.setVariable("map", map);
		int result3 = parser.parseExpression("#map['a'] = 2").getValue(context3, int.class);
		System.out.println("result3=="+result3);
	}
	

}
