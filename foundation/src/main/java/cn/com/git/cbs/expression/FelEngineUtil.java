package cn.com.git.cbs.expression;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.text.StrSubstitutor;

import cn.com.git.cbs.common.utils.DateUtils;
import cn.com.git.cbs.common.utils.NumberUtils;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.common.FelBuilder;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.context.MapContext;
import com.greenpineyu.fel.function.CommonFunction;
import com.greenpineyu.fel.function.Function;

/**
 * Fel表达式操作类
 * 
 * 说明： fel表达式处理时对字符串和数字型区分明显，所以表达式中如果有数字不需要加''，字符串类型需要加'' ，但是不能支持 '数字'这种的比较
 * #{key} == 5000 #{key} == 'aaa' #{key} == '5000' 这种方式禁止,支持不了
 * 
 * @author xia
 *
 */
public class FelEngineUtil {

	private static final PlatformLogger LOGGER = PlatformLogger.create();

	private static FelEngine felEngine;

	/**
	 * 定义strlenfun函数
	 */
	static Function strlenFun = new CommonFunction() {
		public String getName() {
			return "strlen";
		}

		/*
		 * 调用strlen("xxx")时执行的代码
		 */
		@Override
		public Object call(Object[] arguments) {
			Object msg = null;
			if (arguments != null && arguments.length > 0) {
				msg = arguments[0];
			}
			return msg.toString().length();
		}
	};

	// 定义substr函数
	static Function substrFun = new CommonFunction() {

		public String getName() {
			return "substr";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments != null && arguments.length > 0) {
				String str = (String) arguments[0];
				int x = (int) arguments[1];
				int y = (int) arguments[2];

				x = (x < 0) ? 0 : x;
				y = (y < 0) ? 0 : y;

				x = (x > str.length()) ? str.length() : x;
				y = (y > str.length()) ? str.length() : y;

				y = (y < x) ? x : y;
				return str.substring(x, y);
			}
			return "";
		}
	};

	// 定义choose函数
	static Function chooseFun = new CommonFunction() {

		public String getName() {
			return "choose";
		}

		@Override
		public Object call(Object[] arguments) {
			for (int i = 0; i < arguments.length; i++) {
				if (arguments[i] != null && !"".equals(arguments[i])) {
					return arguments[i];
				}
			}
			return "";
		}
	};

	// 定义add函数
	static Function addFun = new CommonFunction() {

		public String getName() {
			return "add";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "" || arguments[1] == "") { // 如果有参数为空就返回空
				return 0;
			}
			// 如果不是数字，直接返回0
			if (isNaN(arguments[0].toString()) || isNaN(arguments[1].toString())) {
				return 0;
			}
			return Double.parseDouble(arguments[0].toString()) + Double.parseDouble(arguments[1].toString());
		}
	};

	// 定义toInt函数
	// int型强转，1.1和1.8都会转成1，如果需要四舍五入，到时候再改
	static Function toIntFun = new CommonFunction() {

		public String getName() {
			return "toInt";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "") { // 如果有参数为空就返回空
				return 0;
			}
			// isNaN 如果是数字则返回 false
			if (isNaN(arguments[0].toString())) {
				return 0;
			} else {
				// 转换科学计数法
				BigDecimal db = new BigDecimal(arguments[0].toString());
				String bigStr = db.toPlainString();
				// return Integer.parseInt(arguments[0].toString());
				return (int) NumberUtils.toDouble(bigStr);
			}
		}
	};

	// 定义atoi函数
	// int型强转，1.1和1.8都会转成1，如果需要四舍五入，到时候再改
	static Function atoiFun = new CommonFunction() {

		public String getName() {
			return "atoi";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "") { // 如果有参数为空就返回空
				return 0;
			}
			// isNaN 如果是数字则返回 false
			if (isNaN(arguments[0].toString())) {
				return 0;
			} else {
				// 转换科学计数法
				BigDecimal db = new BigDecimal(arguments[0].toString());
				String bigStr = db.toPlainString();
				return (int) NumberUtils.toDouble(bigStr);
			}
		}
	};

	// 定义toNum函数
	static Function toNumFun = new CommonFunction() {

		public String getName() {
			return "toNum";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "") { // 如果有参数为空就返回空
				return 0;
			}
			// isNaN 如果是数字则返回 false
			if (isNaN(arguments[0].toString())) {
				return 0;
			} else {
				// 转换科学计数法
				BigDecimal db = new BigDecimal(arguments[0].toString());
				String bigStr = db.toPlainString();
				return NumberUtils.toDouble(bigStr);
			}
		}
	};

	// 定义toDouble函数
	static Function toDoubleFun = new CommonFunction() {

		public String getName() {
			return "toDouble";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "") { // 如果有参数为空就返回空
				return 0;
			}
			// isNaN 如果是数字则返回 false
			if (isNaN(arguments[0].toString())) {
				return 0;
			} else {
				// 转换科学计数法
				BigDecimal db = new BigDecimal(arguments[0].toString());
				String bigStr = db.toPlainString();
				return NumberUtils.toDouble(bigStr);
			}
		}
	};

	// 定义date_f函数
	/**
	 * 函 数 名 date_f ( p1 , p2 , p3 ) 函 数 名 date_f ( p1 , p2 , p3 ) 参数说明 p1 = 1
	 * 求日期加天数 p2 字段名, p3 整数字段名或常量
	 * 
	 * p1 = 2 求日期减天数 p2 字段名, p3 整数字段名或常量
	 * 
	 * p1 = 4 求年加上p3 举 例 日期加一 ＝ date_f(1,#{date1},30) 日期减一 ＝
	 * date_f(2,#{date2},30) 日期的年加 = date_f(4,#{date2},30)
	 */
	static Function date_fFun = new CommonFunction() {

		public String getName() {
			return "date_f";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "" || arguments[1] == "" || arguments[2] == "") { // 如果有参数为空就
																					// 返回空
				return "";
			}

			if ("1".equals(arguments[0].toString())) {
				return DateUtils.addDays(arguments[1].toString(), (int) arguments[2]);
			} else if ("2".equals(arguments[0].toString())) {
				return DateUtils.subDays(arguments[1].toString(), (int) arguments[2]);
			} else if ("4".equals(arguments[0].toString())) {
				return DateUtils.addYears(arguments[1].toString(), (int) arguments[2]);
			}

			return "";

		}
	};

	/**
	 * 计算天数差的函数，通用 后面的参数减去前面的参数，如果前面的日期大，需要返回负数天数
	 * 
	 * @param sDate1
	 * @param sDate2
	 * @returns 返回天数差
	 */
	static Function dateDiffFun = new CommonFunction() {

		public String getName() {
			return "dateDiff";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "" || arguments[1] == "") { // 如果有参数为空就 返回空
				return "";
			}

			return DateUtils.dateDiff(arguments[0].toString(), arguments[1].toString());

		}
	};

	/**
	 * 判断字符串包含 str1 中包含 str2，则返回 1 ， 否则返回 0
	 */
	static Function strstrFun = new CommonFunction() {

		public String getName() {
			return "strstr";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "" || arguments[1] == "") { // 如果有参数为空就 返回空
				return 0;
			}

			String str1 = arguments[0].toString();
			String str2 = arguments[1].toString();

			if (str1.contains(str2)) {
				return 1;
			} else {
				return 0;
			}

		}
	};

	/**
	 * 整数取模
	 * 
	 * @param num1
	 *            整型变量或整数
	 * @param num2
	 *            整型变量或整数
	 */
	static Function modFun = new CommonFunction() {

		public String getName() {
			return "mod";
		}

		@Override
		public Object call(Object[] arguments) {
			if (arguments[0] == "" || arguments[1] == "") { // 如果有参数为空就 返回空
				return 0;
			}

			return (int) arguments[0] % (int) arguments[1];

		}
	};

	static {
		FelBuilder.bigNumberEngine();
		felEngine = new FelEngineImpl();
		// 添加函数到引擎中。
		felEngine.addFun(strlenFun);
		felEngine.addFun(substrFun);
		felEngine.addFun(chooseFun);
		felEngine.addFun(addFun);
		felEngine.addFun(toIntFun);
		felEngine.addFun(atoiFun);
		felEngine.addFun(toNumFun);
		felEngine.addFun(toDoubleFun);
		felEngine.addFun(date_fFun);
		felEngine.addFun(dateDiffFun);
		felEngine.addFun(strstrFun);
		felEngine.addFun(modFun);

	}

	/**
	 * 判断是否是数字型 数字返回 false （int、double） 非数字返回 true
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNaN(String str) {
		return !NumberUtils.isCreatable(str);
	}

	/**
	 * 执行eval之前，先把map中的值转换为ctx,String类型的 key为了兼容以前的表达式，需要替换成#{key1}方式取值
	 * 
	 * @param ctx
	 *            表达式上下文
	 * @param map
	 *            变量Map
	 * @param <T>
	 *            泛型
	 * @return
	 */
	private static <T> FelContext MapToFelContextString(FelContext ctx, Map<String, T> map) {
		Iterator<Entry<String, T>> iterator = map.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, T> entry = iterator.next();
			String key = entry.getKey();
			String value = (String) entry.getValue();
			ctx.set(key, value);
		}

		return ctx;
	}

	/**
	 * 把原始表达式中的Key根据 #{}符号转换为value
	 * 
	 * @param str
	 *            传过来的原始表达式
	 * @param messageValues
	 *            把表达式中的Key替换为value
	 * @return 替换占位符后的字符串
	 */
	private static String replaceValue(String str, Map<String, Object> messageValues) {
		StrSubstitutor sub = new StrSubstitutor(new CaseInsensitiveObjLookup(messageValues), "#{", "}", '\\');
		String format = str;
		format = format.replaceAll("(?i) AND ", "&&");
		format = format.replaceAll("(?i) OR ", "||");
		format = sub.replace(format);
		return format;
	}

	private static Object eval(String str, Map<String, Object> messageValues) {
		try {			
			str = replaceValue(str, messageValues);

//			FelContext ctx = felEngine.getContext();
//			ctx = MapToFelContextString(ctx, messageValues);
			Object result = felEngine.eval(str);
			
//			MapContext ctx = new MapContext(messageValues);
//			Object result = felEngine.eval(str,ctx);			
			
			return result;
		} catch (Exception e) {
			LOGGER.error(e);
			throw ExceptionUtils.returnError("PL2001", e, e.getMessage());
		}
	}

	/**
	 * 运算，计算表达式结果（string）
	 * 
	 * @param str
	 *            表达式
	 * @param messageValues
	 *            变量Map
	 * @return String 公共的返回结果
	 */
	public static String evalString(String str, Map<String, Object> messageValues) {
		return eval(str, messageValues).toString();
	}

	/**
	 * 逻辑运算，计算表达式结果是ture或者flase
	 * 
	 * @param str
	 *            表达式
	 * @param messageValues
	 *            变量Map
	 * @return Boolean 公共的返回结果
	 */
	public static Boolean evalBoolean(String str, Map<String, Object> messageValues) {
		return (Boolean) eval(str, messageValues);
	}

	/**
	 * 运算，计算表达式结果是BigDecimal
	 * 
	 * @param str
	 *            表达式
	 * @param messageValues
	 *            变量Map
	 * @return BigDecimal 公共的返回结果
	 */
	public static BigDecimal evalBigDecimal(String str, Map<String, Object> messageValues) {
		if (eval(str, messageValues).toString().contains(".")) {
			return new BigDecimal((double) eval(str, messageValues));
		} else {
			return new BigDecimal((int) eval(str, messageValues));
		}
	}

}
