/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import cn.com.git.cbs.engine.datamodel.TBL_Subtxdef;

/**
 * 表subtxdef的dao接口
 */
public interface TBL_SubtxdefDao {  				
	
	/**
	 * 根据Where条件查询一条数据
	 * Where条件字段：wuid
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from SUBTXDEF
	 * 		where  1=1
	 * 			<if test="wuid!= null" >
	 * 				 AND WUID = #{wuid, jdbcType=CHAR}
	 * 			</if>
	 * </xmp>}
	 * @param subtxdef 表对象
	 * @return TBL_Subtxdef 查询结果
	 */
	public TBL_Subtxdef selSubtxdefByIdx1(TBL_Subtxdef subtxdef);
							
}
