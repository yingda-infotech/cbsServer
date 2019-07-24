/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

import cn.com.git.cbs.log.PlatformLogger;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 
 * @author Dengjia
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	/***
	 * 默认分隔符：{@value}
	 */
	private static final char SEPARATOR = '_';
	/***
	 * 默认字符编码：{@value}
	 */
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 *            字符串
	 * @return 默认编码的字节数组
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为字符串
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 根据默认编码编制的字符串
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/***
	 * 使用String.format方法格式化字符串
	 * 
	 * @param format
	 *            格式字符串
	 * @param args
	 *            格式字符串中由格式说明符引用的参数。如果还有格式说明符以外的参数，则忽略这些额外的参数。参数的数目是可变的，可以为 0。
	 * @return 一个格式化字符串。如果格式字符串中包含非法语法、与给定的参数不兼容的格式说明符，格式字符串给定的参数不够，或者存在其他非法条件，则返回null
	 *         。
	 * @see String#format(String, Object...)
	 */
	public static String format(String format, Object... args) {
		if (format == null) {
			LOGGER.debug("无效输入项");
			return null;
		}
		String ret = null;
		try {
			ret = String.format(format, args);
		} catch (Exception e) {
			LOGGER.debug("无效输入项", e);
		}
		return ret;
	}

	/**
	 * 检查字符串是否存在于一组给定的字符串中，如
	 * 
	 * <pre>
	 * inString(&quot;a&quot;, &quot;abc&quot;, &quot;a&quot;, &quot;abb&quot;) = true;
	 * inString(&quot;d&quot;, &quot;abc&quot;, &quot;a&quot;, &quot;abb&quot;) = false;
	 * inString(null, &quot;abc&quot;, &quot;a&quot;, &quot;abb&quot;) = false;
	 * inString(&quot;a&quot;, &quot;abc&quot;, &quot; a&quot;, &quot;abb&quot;) = false;
	 * </pre>
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true,如果不包含或者验证字符串为null则返回false;
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(s)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 * 
	 * @param html
	 *            传入的包含HTML标签的字符串
	 * @return 去掉HTML标签的字符串
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param html
	 *            传入的包含HTML标签的字符串
	 * @return 替换为手机识别的HTML字符串
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return 缩略字符串
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e);
		}
		return "";
	}

	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				LOGGER.error(e);
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result.replaceAll(
				"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
				"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = new ArrayList<String>();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}

	/**
	 * 驼峰命名法工具，首字母小写
	 * 
	 * <pre>
	 * toCamelCase("hello_world") == "helloWorld"
	 * toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * toUnderScoreCase("helloWorld") = "hello_world"
	 * </pre>
	 * 
	 * @param s
	 *            以默认分割符{@link StringUtils#SEPARATOR}分割的字符串
	 * 
	 * @return 小驼峰法名称
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具，首字母大写
	 * 
	 * <pre>
	 * toCamelCase("hello_world") == "helloWorld"
	 * toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * toUnderScoreCase("helloWorld") = "hello_world"
	 * </pre>
	 * 
	 * @param s
	 *            以默认分割符{@link StringUtils#SEPARATOR}分割的字符串
	 * 
	 * @return 大驼峰法名称
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具，小写单词以下划线分割
	 * 
	 * <pre>
	 * toCamelCase("hello_world") == "helloWorld"
	 * toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * toUnderScoreCase("helloWorld") = "hello_world"
	 * </pre>
	 * 
	 * @param s
	 *            驼峰法名称
	 * 
	 * @return 以默认分割符{@link StringUtils#SEPARATOR}分割的下划线风格字符串
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 如果源字符串不为空，则设置值
	 * 
	 * @param target
	 *            目标字符串对象
	 * @param source
	 *            源字符串
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
			target = source;
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * 
	 * <pre>
	 * 对象串 例如：row.user.id
	 * 返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 * </pre>
	 * 
	 * @param objectString
	 *            对象串
	 * @return JS三目运算字符串
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append(".").append(vals[i]);
			result.append("!").append(val.substring(1)).append("?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * 将java字符串二维数组转换生成一个javascript数组
	 * 
	 * @param sValue
	 *            被转化字符串数组
	 * @return 转化之后的js数组
	 */
	public static String generateJSArray(String[][] sValue) {
		if (sValue == null) {
			sValue = new String[0][0];
		}
		StringBuffer buff = new StringBuffer(4096);
		buff.append("new Array(");
		String[] ss;
		String s;
		for (int i = 0; i < sValue.length; i++) {
			ss = sValue[i];
			if (ss == null) {
				ss = new String[0];
			}
			if (i > 0) {
				buff.append(",");
			}
			buff.append("new Array(");
			for (int j = 0; j < ss.length; j++) {
				if (j > 0) {
					buff.append(",");
				}
				s = ss[j];
				if (s == null) {
					s = "&nbsp;";
				} else if ("".equals(s.trim())) {
					s = "&nbsp;";
				}
				buff.append("\"").append(StringEscapeUtils.escapeJava(s)).append("\"");
			}
			buff.append(")");
		}
		buff.append(")");
		return buff.toString();
	}

	/**
	 * 将一句字符串语句转换成标准的英文语句，即每个单词的首字母大写，其他的字母小写，单词之间用空格隔开
	 * 
	 * <pre>
	 * formatClause(&quot;THIS IS A TEST CASE&quot;) == &quot;This Is A Test Case&quot;
	 * </pre>
	 * 
	 * @param clause
	 *            需转化的字符串
	 * @return 转化之后的字符串
	 */
	public static String formatClause(String clause) {
		StringBuffer sbRet=new StringBuffer(clause.length()+1);
		String ret = "";
		String tem = "";
		if (clause != null && !"".equals(clause)) {
			StringTokenizer st = new StringTokenizer(clause, " ");
			while (st.hasMoreElements()) {
				tem = st.nextToken();
				if (!"".equals(tem.trim())) {
					sbRet.append(capitalize(tem).trim());
					sbRet.append(" ");
				}
			}
			ret = sbRet.substring(0, sbRet.length() - 1);
		}
		return ret;
	}
}
