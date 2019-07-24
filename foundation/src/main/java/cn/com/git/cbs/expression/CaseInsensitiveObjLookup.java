/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.expression;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrLookup;

import cn.com.git.cbs.common.utils.NumberUtils;
import cn.com.git.cbs.log.PlatformLogger;

/**
 * 表达式取值时忽略key 大小写的类 map中所有的key都要小写
 * 和CaseInsensitiveStrLookup的区别在于js表达式值都是转换为''字符串进行处理，不区分字符串，但是fel处理需要区分类型
 * 
 * @author xia
 */
public class CaseInsensitiveObjLookup extends StrLookup<Object> {
	private final static PlatformLogger LOGGER=PlatformLogger.create();

	private final Map<String, Object> map;

	/**
	 * 表达式取值时忽略key 大小写
	 * 
	 * @param map
	 */
	CaseInsensitiveObjLookup(final Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * 表达式取值时忽略key 大小写,返回表达式的值 数字型不加 '单引号 非数字型需要加上''
	 */
	@Override
	public String lookup(final String key) {
		String lowercaseKey = key.toLowerCase(); // lowercase the key you're
													// looking for
		if (map == null) {
			return null;
		}
		final Object obj = map.get(lowercaseKey);
		if (obj == null) {
			return "''";
		}
		
		if (obj instanceof List) {
			LOGGER.warn("暂时不支持List类型，key=[%s]",key);
			return null;
		}

		StringBuffer sb = new StringBuffer();
		String value=obj.toString();
		if (!NumberUtils.isCreatable(value)) {
			sb.append("'");
			sb.append(value);
			sb.append("'");
		} else {
			sb.append(value);
		}
		return sb.toString();
	}
}
