/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.git.cbs.common.utils.NumberUtils;
import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.ExceptionUtils;
import cn.com.git.cbs.tpmanager.DataPool;

/***
 * 默认的数据缓冲池实现
 * 
 * @author DengJia
 *
 */
class DataPoolImpl implements DataPool {

	// 值Map
	private DataObject dataObject;
	/***
	 * 当前子交易的标识
	 */
	private String wumark = null;

	/***
	 * 默认构造器
	 */
	public DataPoolImpl() {
		dataObject = new DataObject();
	}

	/**
	 * 从缓冲池中获取对象，如果子交易标识不为空则使用wumark.key作为键值，否则使用传入参数作为键值。该键值会被强制转换为小写。
	 * 
	 * @param key
	 *            对象的键值
	 * @return 对象实例，如果键值为空或者缓冲池中不存在该键值则返回null
	 */
	private Object get(String key) {
		return this.get(key, true);
	}

	/***
	 * 从缓冲池中获取对象
	 * 
	 * @param key
	 *            对象的键值
	 * @param useMark
	 *            是否使用子交易标识
	 * @return 对象实例，如果键值为空或者缓冲池中不存在该键值则返回null
	 */
	private Object get(String key, boolean useMark) {
		if (org.apache.commons.lang3.StringUtils.isBlank(key)) {
			throw ExceptionUtils.returnError("PL4001", key);
		}
		key = key.toLowerCase();
		if (useMark) {
			String maskKey = org.apache.commons.lang3.StringUtils.join(wumark, ".", key).toLowerCase();
			if (this.wumark != null && this.dataObject.containsKey(maskKey)) {
				return this.dataObject.get(maskKey);
			} else {
				return this.dataObject.get(key);
			}
		} else {
			return this.dataObject.get(key);
		}
	}

	/**
	 * 向缓冲池中设置对象的值，如果子交易标识不为空则使用wumark.key作为键值，否则使用传入参数作为键值。该键值会被强制转换为小写。
	 * 
	 * @param key
	 *            对象的键值
	 * @param value
	 *            对象实例
	 */
	private void set(String key, Object value) {
		this.set(key, value, true);
	}

	/**
	 * 向缓冲池中设置对象的值
	 * 
	 * @param key
	 *            对象的键值
	 * @param value
	 *            对象实例
	 * @param useMark
	 *            是否使用子交易标识
	 */
	private void set(String key, Object value, Boolean useMark) {
		if (org.apache.commons.lang3.StringUtils.isBlank(key)) {
			throw ExceptionUtils.returnError("PL4001", key);
		}
		key = key.toLowerCase();
		if (useMark) {
			if (this.wumark != null) {
				key = org.apache.commons.lang3.StringUtils.join(wumark, ".", key).toLowerCase();
			}
			this.dataObject.set(key, value);
		} else {
			this.dataObject.set(key, value);
		}
	}

	public void setValue(String key, String value, boolean useMark) {
		// TODO: 需要从缓存的公共数据字典中先查找

		if (useMark && wumark != null) {
			key = org.apache.commons.lang3.StringUtils.join(wumark, ".", key);
		}
		if (value == null) {
			value = "";
		}
		dataObject.setString(key.toLowerCase(), value.trim());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.git.cbs.engine.DataPool#setValue(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setValue(String key, String value) {
		this.setValue(key, value, true);
	}

	public String getValue(String key, boolean useMark) {
		Object ret = this.get(key, useMark);
		if (ret != null) {
			return ret.toString().trim();
		} else {
			return "";
		}
	}

	@Override
	public String getValue(String key) {
		return this.getValue(key, true);
	}

	public String getWumark() {
		return wumark;
	}

	public void setWumark(String wumark) {
		this.wumark = wumark;
	}

	public DataObject getDataObject() {
		return dataObject;
	}

	public void setDataObject(DataObject dataObject) {
		this.dataObject = dataObject;
	}

	@Override
	public void setInt(String key, int value) {
		String strValue = String.valueOf(value);
		this.setValue(key, strValue);
	}

	@Override
	public void setInt(String key, String value) {
		if (!org.apache.commons.lang3.math.NumberUtils.isCreatable(value)) {
			throw ExceptionUtils.returnError("PL4002", value);
		}
		setInt(key, org.apache.commons.lang3.math.NumberUtils.toInt(value));
	}

	@Override
	public int getInt(String key) {
		String ret = this.getValue(key);
		if (!org.apache.commons.lang3.math.NumberUtils.isCreatable(ret)) {
			throw ExceptionUtils.returnError("PL4003", key, ret);
		}
		return org.apache.commons.lang3.math.NumberUtils.toInt(ret);
	}

	@Override
	public void setDecimal(String key, String value) {
		setDecimal(key, value, 2, RoundingMode.HALF_UP);
	}

	@Override
	public void setDecimal(String key, String value, int scale, RoundingMode roundingMode) {
		if (!org.apache.commons.lang3.math.NumberUtils.isCreatable(value)) {
			throw ExceptionUtils.returnError("PL4004", value);
		}
		BigDecimal decimalValue = new BigDecimal(value);
		setDecimal(key, decimalValue, scale, roundingMode);
	}

	@Override
	public void setDecimal(String key, BigDecimal value) {
		setDecimal(key, value, 2, RoundingMode.HALF_UP);
	}

	@Override
	public void setDecimal(String key, BigDecimal value, int scale, RoundingMode roundingMode) {
		String strValue = "0.00";
		if (value != null) {
			strValue = value.setScale(scale, roundingMode).toPlainString();
		} else {
			throw ExceptionUtils.returnError("PL4005");
		}
		setValue(key, strValue);
	}

	@Override
	public BigDecimal getDecimal(String key) {
		String ret = this.getValue(key);
		if (!org.apache.commons.lang3.math.NumberUtils.isCreatable(ret)) {
			throw ExceptionUtils.returnError("PL4006", key, ret);
		}
		return org.apache.commons.lang3.math.NumberUtils.createBigDecimal(ret);
	}

	public List<?> getValueAsList(String key) {
		return (List<?>) this.get(key);
	}

	@Override
	public void setList(String key, List<?> value) {
		this.set(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.git.cbs.engine.DataPool#getValueAsList(java.lang.String,
	 * java.lang.Class)
	 */
	@Override
	public <T extends DataObject> List<T> getList(String key, Class<T> clz) {
		// TODO Auto-generated method stub
		String maskKey = org.apache.commons.lang3.StringUtils.join(wumark, ".", key).toLowerCase();
		if (wumark != null && dataObject.containsKey(maskKey)) {
			return dataObject.getList(maskKey, clz);
		} else {
			return dataObject.getList(key, clz);
		}
	}

	/**
	 * 
	 * 把fld的列表解析成list格式
	 * 
	 * { title= trancode= fld=Ccy~~~~~ fld=Ticket~~~~~ fld=TranAmt~~~~~
	 * fld=DenomNum~~~~~ fld=OthAmt~~~~~ fld=DenomNumF~~~~~ }
	 * '01'~'001'~800.00~0~0~0&& '01'~'001'~800.00~0~0~0&&
	 * '01'~'001'~800.00~0~0~0&& '01'~'001'~800.00~0~0~0&&
	 * '01'~'001'~800.00~0~0~0
	 */
	@Override
	public <T extends DataObject> List<T> getStringList(String key, Class<T> clz) {
		String str = dataObject.getString(key);
		if (null == str) {
			return new ArrayList<T>();
		}
		// 解析fld
		String[] fldStrs = str.split("}");

		// 获取fld属性名称
		String fldStr = fldStrs[0];
		fldStr = fldStr.replace("{", "");
		fldStr = fldStr.replace("title=", "");
		fldStr = fldStr.replace("trancode=", "");
		fldStr = fldStr.replace("fld=", "");
		String[] flds = fldStr.split("~~~~~");

		// 获取属性值
		String valStr = fldStrs[1]; // 多条数据
		String[] vars = valStr.split("&&");

		List<T> list = new ArrayList<T>();
		for (int i = 0; i < vars.length; i++) {
			String var = vars[i];
			String[] fileds = var.split("~");
			T da = BeanUtils.instantiateClass(clz);
			for (int j = 0; j < fileds.length; j++) {
				// 用值的长度不用赋值，不用属性的来做是因为属性按照~~~~~切割，最后会多出一个为空的值
				da.set(flds[j], fileds[j]);
			}
			list.add(da);
		}
		return list;
	}

	public void setValues(DataObject source, String... keys) {
		if (source != null) {
			for (String key : keys) {
				this.set(key, source.get(key),true);
			}
		}
	}

	public void getValues(DataObject target, String... keys) {
		if (target != null) {
			for (String key : keys) {
				target.set(key, this.get(key,true));
			}
		}
	}

	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public String getCommonValue(String key) {
	// return getValue(key, false);
	// }

	/**
	 * 设置缓冲池公共区变量的值
	 * 
	 * @param key
	 *            公共区变量的key
	 * @param value
	 *            公共区变量的value
	 */
	// public void setCommonValue(String key, String value) {
	// this.set(key, value, false);
	// }
}
