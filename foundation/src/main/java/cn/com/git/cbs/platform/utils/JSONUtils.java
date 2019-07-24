/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.platform.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;

import cn.com.git.cbs.model.DataObject;

/**
 * json操作类
 * @author DengJia
 *
 */
public class JSONUtils {
	private final static DataObjectCodec codec = new DataObjectCodec();
	static {
		SerializeConfig.getGlobalInstance().put(DataObject.class, codec);
		ParserConfig.getGlobalInstance().putDeserializer(DataObject.class, codec);
	}

	/**
	 * DataObject类型转换为字符串 
	 * @param data DataObject类型参数
	 * @return json字符串
	 */
	public static String dataObject2String(DataObject data) {
		return JSON.toJSONString(data);
	}

	/**
	 * json字符串转换为DataObject
	 * @param jsonString  json字符串
	 * @return DataObject类型参数
	 */
	public static DataObject string2DataObject(String jsonString) {
		return JSON.parseObject(jsonString, DataObject.class);
	}
}
