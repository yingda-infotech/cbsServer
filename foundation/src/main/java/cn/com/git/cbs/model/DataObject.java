/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.BeanUtils;

import cn.com.git.cbs.common.utils.NumberUtils;
import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.exception.CbsRunTimeException;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * 数据对象，作为数据容器和各层间的媒介传递数据. {@code
 * 

<pre>
 * 使用Map作为内部容器，可用键值对的方式动态获取/设置
 * new DataObject().put("a","134");
 * 
</pre>

 * }
 * 
 * @author DengJia
 *
 */
public class DataObject implements Serializable {

	private final static PlatformLogger LOGGER = PlatformLogger.create();

	private static final long serialVersionUID = -3952708207651368323L;

	/*** Map格式的数据容器 ***/
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * 默认构造器
	 */
	public DataObject() {

	}

	/**
	 * 构造器，以传入对象的数据为自身的数据容器，仅拷贝指定的属性键
	 * 
	 * @param obj
	 *            传入对象
	 * @param keys
	 */
	public void importObject(DataObject src, String... keys) {
		for (String key : keys) {
			this.set(key, src.get(key));
		}
	}

	/**
	 * 构造器，以传入对象的数据为自身的数据容器
	 * 
	 * @param obj
	 *            传入对象
	 */
	public DataObject(DataObject obj) {
		this.data = obj.getData();
	}

	/**
	 * 构造器，以传入Map为自身的数据容器
	 * 
	 * @param map
	 *            传入Map
	 */
	public DataObject(Map<String, Object> map) {
		this.data = map;
	}

	/**
	 * 判断是否包含指定的键名
	 * 
	 * @param key
	 *            键
	 * @return 检查结果
	 */
	public boolean containsKey(String key) {
		return data.containsKey(key);
	}

	/**
	 * 返回键值对应的值
	 * 
	 * @param name
	 *            键值
	 * @return 值，当键值不存在时为null
	 * @see Map#get(Object)
	 */
	public Object get(String name) {
		return data.get(name);
	}

	public Boolean getBoolean(String name) {
		return (Boolean) get(name);
	}

	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * 返回指定值关联的DataObject对象，如果
	 * 
	 * @param name
	 *            与指定值关联的键
	 * @return DataObject对象
	 */
	@SuppressWarnings("unchecked")
	public DataObject getDataObject(String name) {
		Object obj = data.get(name);
		if (obj == null) {
			return null;
		}

		if (obj instanceof DataObject) {
			return (DataObject) obj;
		} else if (obj instanceof Map) {
			DataObject d = new DataObject();
			d.setData((Map<String, Object>) obj);
			return d;
		} else {
			throw new CbsRunTimeException("999999",
					StringUtils.format("名称为%s的对象类型转换出现错误，其类型为%s", name, obj.getClass().getCanonicalName()));
		}
	}

	/**
	 * 返回指定值关联的DataObject对象，如果
	 * 
	 * @param name
	 *            与指定值关联的键
	 * @param clazz
	 *            DataObject对象的类型
	 * @param <T>
	 *            DataObject泛型
	 * @return T DataObject对象
	 */
	@SuppressWarnings("unchecked")
	public <T extends DataObject> T getDataObject(String name, Class<T> clazz) {
		Object obj = data.get(name);
		if (obj == null) {
			// TODO:warning
			return null;
		}
		if (obj.getClass().equals(clazz)) {
			return (T) obj;
		} else {
			T da = (T) BeanUtils.instantiateClass(clazz);
			if (obj instanceof DataObject) {
				da.setData(((DataObject) obj).getData());
			} else if (obj instanceof Map) {
				da.setData((Map<String, Object>) obj);
			} else {
				throw new CbsRunTimeException("999999", StringUtils.format("名称为%s的对象类型转换出现错误，希望转换至%s，实际类型为%s", name,
						clazz.getCanonicalName(), obj.getClass().getCanonicalName()));
			}
			return da;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends DataObject> List<T> getList(String name, Class<T> clazz) {
		List<?> l = (List<?>) data.get(name);
		if (l == null) {
			return new ArrayList<T>();
		}
		List<T> r = new ArrayList<T>();
		if (l.size() > 0) {
			for (Object o : l) {
				if (o.getClass().equals(clazz)) {
					r = (List<T>) l;
					break;
				} else {
					T da = BeanUtils.instantiateClass(clazz);
					if (o instanceof DataObject) {
						da.setData(((DataObject) o).getData());
					} else if (o instanceof Map) {
						da.setData((Map<String, Object>) o);
					} else {
						throw new RuntimeException(name + "类型转换出现错误,obj=" + o.getClass());
					}
					r.add(da);
				}
			}
		}
		return r;
	}

	/**
	 * 根据键获取关联值(Number类型)
	 * 
	 * @param name
	 *            key
	 * @return value
	 */
	public Number getNumber(String name) {
		Object val = get(name);
		if (val == null) {
			return null;
		}

		if (val instanceof Number) {
			return (Number) val;
		} else {
			return NumberUtils.createNumber(val.toString());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRawList(String name) {
		return (List<Map<String, Object>>) (data.get(name));
	}

	/**
	 * 根据键获取关联值(Number类型)
	 * 
	 * @param name
	 *            key
	 * @return value
	 */
	public Date getDate(String name) {
		Object val = get(name);
		if (val == null) {
			return null;
		}

		if (val instanceof Date) {
			return (Date) val;
		} else {
			throw new RuntimeException(name + "类型转换出现错误,obj=" + val.getClass());
		}
	}

	/**
	 * 根据键获取关联值(字符串类型)
	 * 
	 * @param name
	 *            key
	 * @return value
	 */
	public String getString(String name) {
		if (data.containsKey(name)) {
			String value = data.get(name).toString();
			if (value != null) {
				value = value.trim();
			}
			return value;
		} else {
			return null;
		}
	}

	/**
	 * 保存DataObject 的data到data中（putAll）
	 * 
	 * @param obj
	 *            需要被引入的DataObject对象
	 */
	public void importObject(DataObject obj) {
		if (obj != null) {
			this.data.putAll(obj.getData());
		} else {
			LOGGER.warn("传入参数为NULL！");
		}
	}

	/**
	 * 
	 * 将指定键移除
	 * 
	 * @param key
	 *            键
	 * @return 与指定键关联的值
	 * @see Map#remove(Object)
	 */
	public Object remove(String key) {
		return data.remove(key);
	}

	/**
	 * 将指定的值与此映射中的指定键关联.
	 * 
	 * @param name
	 *            与指定值关联的键
	 * @param value
	 *            与指定键关联的值
	 * @see Map#put(Object, Object)
	 */
	public void set(String name, Object value) {
		data.put(name, value);
	}

	public void setBoolean(String name, Boolean value) {
		set(name, value);
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * 将指定的DataObject对象与此映射中的指定键关联.
	 * 
	 * @param name
	 *            与指定值关联的键
	 * @param value
	 *            与指定键关联的DataObject
	 * @see Map#put(Object, Object)
	 */
	public void setDataObject(String name, DataObject value) {
		data.put(name, value.getData());
	}

	/**
	 * 保存list的数据到data中
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            List类型的值对象
	 */
	@SuppressWarnings("unchecked")
	public void setList(String key, List<?> value) {
		if (value != null) {
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>(value.size());
			for (int i = 0; i < value.size(); i++) {
				Object o = value.get(i);
				if (o == null) {
					throw ExceptionUtils.returnError("PL4007", i);
				}
				if (o instanceof DataObject) {
					set(key, value);
					return;
				} else if (o instanceof Map) {
					data.add((Map<String, Object>) o);
				} else {
					throw ExceptionUtils.returnError("PL4008", i, o.getClass().getCanonicalName());
				}
			}
			set(key, data);
		} else {
			LOGGER.warn("传入值对象为NULL！");
		}
	}

	public void setNumber(String name, Number num) {
		set(name, num);
	}

	public void setDate(String name, Date date) {
		set(name, date);
	}

	public void setString(String name, String value) {
		data.put(name, value);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, new RecursiveToStringStyle());
	}
}