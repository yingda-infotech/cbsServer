/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.platform.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ListSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.com.git.cbs.model.DataObject;

/**
 * @author DengJia
 *
 */
public class DataObjectCodec implements ObjectSerializer, ObjectDeserializer {

	private final static ListSerializer lstSerializer = new ListSerializer();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alibaba.fastjson.serializer.ObjectSerializer#write(com.alibaba.
	 * fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object,
	 * java.lang.reflect.Type, int)
	 */
	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
			throws IOException {
		SerializeWriter out = serializer.out;
		DataObject data = (DataObject) object;
		if (object == null || data.getData() == null) {
			out.writeNull();
			return;
		}

		Map<String, Object> map = data.getData();

		SerialContext parent = serializer.getContext();
		serializer.setContext(parent, object, fieldName, 0);
		try {
			out.write('{');

			serializer.incrementIndent();

			Class<?> preClazz = null;
			ObjectSerializer preWriter = null;

			boolean first = true;

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				Object value = entry.getValue();
				String entryKey = entry.getKey();

				if (entryKey == null || value == null) {
					continue;
				}

				if (!first) {
					out.write(',');
				}

				if (out.isEnabled(SerializerFeature.PrettyFormat)) {
					serializer.println();
				}
				out.writeFieldName(entryKey, true);

				first = false;

				Class<?> clazz = value.getClass();

				if (clazz == preClazz) {
					preWriter.write(serializer, value, entryKey, null, 0);
				} else {
					preClazz = clazz;
					if (clazz.isAssignableFrom(List.class)) {
						preWriter = lstSerializer;
						lstSerializer.write(serializer, value, entryKey, null, 0);
					} else {
						preWriter = serializer.getObjectWriter(clazz);
						preWriter.write(serializer, value, entryKey, null, 0);
					}
				}
			}
		} finally {
			serializer.setContext(parent);
		}
		serializer.decrementIdent();
		if (out.isEnabled(SerializerFeature.PrettyFormat) && map.size() > 0) {
			serializer.println();
		}
		out.write('}');

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alibaba.fastjson.parser.deserializer.ObjectDeserializer#deserialze(
	 * com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
		JSONObject jsonObj = parser.parseObject();
		return (T) json2Data(jsonObj);
	}

	private DataObject json2Data(JSONObject jsonObj) {
		DataObject ret = new DataObject();
		for (String key : jsonObj.keySet()) {
			if (jsonObj.get(key) instanceof JSONArray) {
				List<DataObject> retList = new ArrayList<DataObject>();
				for (Object jsonLine : (JSONArray) jsonObj.get(key)) {
					retList.add(json2Data((JSONObject) jsonLine));
				}
				ret.set(key, retList);
			} else {
				ret.set(key, jsonObj.get(key));
			}
		}
		return ret;
	}

	@Override
	public int getFastMatchToken() {
		// TODO Auto-generated method stub
		return 0;
	}

}
