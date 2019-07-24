package cn.com.git.cbs.expression;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlContext;
import org.apache.ibatis.ognl.OgnlException;

import cn.com.git.cbs.log.PlatformLogger;

public class OgnlTest {

	private static final PlatformLogger LOGGER = PlatformLogger.create();
	
	public void main()
    {
//		runOgnl(); 
		testLength();
    }
	
	public static void runOgnl() {
		OgnlContext context = new OgnlContext();
		context.put("Amt", 2000); 
//		Map<String, Object> testMap=new HashMap<String, Object>();
		context.put("_02_Acc", 100.0f);
//	    context.put("_02",testMap);
//	    long begin=Calendar.getInstance().getTimeInMillis();
//	    for(int i=0;i<10000;i++) {
			try {
		    	Object object = Ognl.parseExpression("#Amt * #_02_Acc+(1/2)");
				Ognl.getValue(object, context, context.getRoot());

			    Calendar.getInstance().getTimeInMillis();
			} catch (OgnlException e) {
				LOGGER.error(e);
//			}
	    }
//	    System.out.println("Total time："+(end-begin));
 
	}
	
	/**
	 * 测试Length方法
	 */
	public static void testLength() {
		
		Map<String, Object> messageValues = new HashMap<String, Object>();
		messageValues.put("amount", "张三哇哈哈哈");
	    StrSubstitutor sub = new StrSubstitutor(new CaseInsensitiveStrLookup(messageValues), "#(", ")", '\\');

	    String format2 = sub.replace("@cn.com.git.cbs.expression.ExpressionUtil@strlen('#(amounT)')==5");
	    System.out.println("the str="+format2);	
		
		OgnlContext context = new OgnlContext();
//		context.put("amounT", 2000); 
			try {
//				  Object min = Ognl.getValue("@java.lang.Math@min(4,10)", context, context.getRoot());
		    	Object object = Ognl.parseExpression(format2);
				Ognl.getValue(object, context, context.getRoot());

			    Calendar.getInstance().getTimeInMillis();
			} catch (OgnlException e) {
				LOGGER.error(e);
//			}
	    }
		
	}
}
