/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import java.util.List;

import cn.com.git.cbs.engine.datamodel.TBL_Tranflow;

/**
 * 表tranflow的dao接口
 */
public interface TBL_TranflowDao {  				
	
	/**
	 * 根据Where条件查询多条数据
	 * Where条件字段：muid
	 * 排序字段：muid,workseq
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from TRANFLOW
	 * 		where  1=1
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 		 ORDER BY muid,workseq
	 * </xmp>}
	 * @param tranflow 表对象
	 * @return 查询结果
	 */
	public List<TBL_Tranflow> selTranflowListByIdx1(TBL_Tranflow tranflow);
							
}
