/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.tpmanager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import cn.com.git.cbs.model.DataObject;

/***
 * 平台提供的数据缓冲池接口，用于交易联动以及子交易的输入输出
 * 
 * @author DengJia
 *
 */
public interface DataPool {
	
	/**
	 * 设置缓冲池中的数值.
	 * 如果键值不存在则在缓冲池中创建一个键值，否则更新该键值
	 * @param key 键值
	 * @param value 数值
	 * 
	 */
	public void setValue(String key, String value);

	
	/***
	 * 获取缓冲池中的数值.
	 * 如果key不存在则返回null
	 * @param key 键值
	 * @return 如果key不存在则返回null
	 */
	public String getValue(String key);
	
//	/**
//	 * 获取缓冲池公共区变量的值.
//	 * 如果key不存在则返回null
//	 * @param key  公共区变量的key
//	 * @return 公共区变量的值
//	 */
//	public String getCommonValue(String key);

	public void setInt(String key, int value);

	public void setInt(String key, String value);

	public int getInt(String key);

	public void setDecimal(String key, String value);

	public void setDecimal(String key, String value, int scale,
			RoundingMode roundingMode);

	public void setDecimal(String key, BigDecimal value);

	public void setDecimal(String key, BigDecimal value, int scale, 
			RoundingMode roundingMode);

	public BigDecimal getDecimal(String key);
	
	public<T extends DataObject> List<T> getList(String key,Class<T> clz);
	
	public void setList(String key,List<?> value);
	
	/**
	 * 把柜面传来的string格式的上传列表，转为list的数据.
	 * list数据格式如下：<p>
	 * {@code
	 * { title= trancode= fld=Ccy~~~~~ fld=Ticket~~~~~ fld=TranAmt~~~~~ fld=DenomNum~~~~~ fld=OthAmt~~~~~ fld=DenomNumF~~~~~ } 
	 * '01'~'001'~500.00~0~0~0&&'01'~'002'~200.00~0~0~0&&'01'~'005'~100.00~0~0~"
	 * }
	 * @param key 键值
	 * @param clz list中数据的类型
	 * @param <T> list中数据的类型
	 * @return list数据
	 */
	public<T extends DataObject> List<T> getStringList(String key,Class<T> clz);
	
	/***
	 * 从源对象中取出相应的key和对应的值，并设置到缓冲池中
	 * @param source 源对象
	 * @param keys 键值
	 */
	public void setValues(DataObject source, String... keys);
	
	/***
	 * 从缓冲池中取出键值对应的值，并设置到目标对象的键上
	 * @param target 目标对象
	 * @param keys 键值
	 */
	public void getValues(DataObject target, String... keys);
}