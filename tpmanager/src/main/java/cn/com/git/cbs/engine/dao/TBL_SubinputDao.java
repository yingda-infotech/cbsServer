/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import java.util.List;

import cn.com.git.cbs.engine.datamodel.TBL_Subinput;

/**
 * 表subinput的dao接口
 */
public interface TBL_SubinputDao {  				
	
	/**
	 * 根据Where条件查询多条数据
	 * Where条件字段：muid,wumark
	 * 排序字段：localname
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from SUBINPUT
	 * 		where  1=1
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="wumark!= null" >
	 * 				 AND WUMARK = #{wumark, jdbcType=CHAR}
	 * 			</if>
	 * 		 ORDER BY localname
	 * </xmp>}
	 * @param subinput 表对象
	 * @return 查询结果
	 */
	public List<TBL_Subinput> selSubinputListByIdx1(TBL_Subinput subinput);
							
}
