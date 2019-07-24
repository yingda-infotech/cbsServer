/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.expression;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.core.io.ClassPathResource;

import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * 
 * JS表达式操作类
 * 
 * <pre>
 * 提供逻辑表达式的比较和业务表达式的计算
 * </pre>
 * 
 * @author xia
 */

public class JsExpressionUtil{
	private static final PlatformLogger LOGGER = PlatformLogger.create();

	private static ScriptEngine engine;

	private static final String EXPRESSIONPATH = "/META-INF/expression.js";

	static {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName("javascript");

		Reader read = null;
		try {
			read = new InputStreamReader(
					new ClassPathResource(EXPRESSIONPATH).getInputStream());
			try {
				engine.eval(read);
			} catch (ScriptException e) {
				LOGGER.error(e.getMessage(), e);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				read.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * 逻辑运算，计算表达式结果是ture或者flase
	 * 
	 * @param str 表达式
	 * @param messageValues 传入的变量值
	 * @return Object 公共的返回结果
	 */
	private static Object eval(String str, Map<String, Object> messageValues) {
		LOGGER.debug("original expression=[%s]", str);
		StrSubstitutor sub = new StrSubstitutor(
				new CaseInsensitiveStrLookup(messageValues), "#{", "}", '\\');
		String format = str;
		format = format.replaceAll("(?i) AND ", "&&");
		format = format.replaceAll("(?i) OR ", "||");
		format = sub.replace(format);
		LOGGER.debug("expression=[%s]", format);
		try {
			Object ret = engine.eval(format);
			if (StringUtils.inString(ret.toString(), "NaN", "undefined",
					"Infinity", "null")) {
				throw ExceptionUtils.returnError("PL2001", null, "表达式结果异常");
			}
			return ret;
		} catch (ScriptException e) {
			LOGGER.error(e);
			throw ExceptionUtils.returnError("PL2001", e, e.getMessage());
		}
	}

	/**
	 * 运算，计算表达式结果是ture或者flase
	 * 
	 * @param str 表达式
	 * @param messageValues 变量值
	 * @return ReturnObj 公共的返回结果
	 */
	public static boolean evalBoolean(String str,
			Map<String, Object> messageValues) {
		return (boolean) eval(str, messageValues);
	}

	/**
	 * 运算，计算表达式结果（string）
	 * @param str 表达式
	 * @param messageValues 传入的变量值
	 * @return String 公共的返回结果
	 */
	public static String evalString(String str, Map<String, Object> messageValues) {
		return eval(str, messageValues).toString();
	}

	/**
	 * 运算，计算表达式结果(int)
	 * @param str 表达式
	 * @param messageValues 传入的变量值
	 * @return 把计算的结果转换为int型返回
	 */
	public static int evalInt(String str, Map<String, Object> messageValues) {
		double dou = (double) eval(str, messageValues);
		return (int)dou;
		
	}
	
	/**
	 * 运算，计算表达式结果(double)
	 * @param str 表达式
	 * @param messageValues 传入的变量值
	 * @return 把计算的结果转换为double型返回
	 */
	public static double evalDouble(String str, Map<String,Object> messageValues) {
		double dou = (double) eval(str, messageValues);
		return dou;
	}
	
//	/**
//	 * 运算，计算表达式结果(BigDecimal)
//	 * @param str
//	 * @param messageValues
//	 * @return 把计算的结果转换为BigDecimal型返回
//	 */
//	public static BigDecimal evalBigDecimal(String str, Map<String, Object> messageValues) {
//		return (BigDecimal) eval(str, messageValues);
//	}

}
