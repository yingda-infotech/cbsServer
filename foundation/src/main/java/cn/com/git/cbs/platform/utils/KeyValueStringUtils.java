package cn.com.git.cbs.platform.utils;

import java.util.HashMap;
import java.util.Map;

import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.model.DataObject;

/**
 * 自定义xml报文和map的转换类
 * @author DengJia
 *
 */
public class KeyValueStringUtils {
	
	/**
	 * 自定义xml报文字符串转换为map
	 * @param input 字符串{@code<pre><Ccy2>01</>格式的自定义xml报文</pre>}
	 * @return	{@code<pre>Map<String, Object></pre>} Map对象
	 */
	public static Map<String, Object> toMap(String input) {
		Map<String, Object> ret = new HashMap<>();
		String[] keyValues = input.split("</>");
		for (String keyAndValue : keyValues) {
			String key = keyAndValue.substring(1, keyAndValue.indexOf(">"));
			String value = keyAndValue.substring(keyAndValue.indexOf(">") + 1);
			ret.put(key, value);
		}
		return ret;
	}

	/**
	 * DataObject转换为自定义xml报文
	 * @param obj DataObject对象
	 * @return <code><pre><Ccy2>01</>格式的自定义xml报文</pre></code>
	 */
	public static String toKeyPairString(DataObject obj) {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> map = obj.getData();
		for (String key : map.keySet()) {
			String value = StringUtils.defaultIfBlank(obj.getString(key), "");
			sb.append("<").append(key).append(">").append(value).append("</>");
		}
		return sb.toString();
	}
}
