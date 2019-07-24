/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import cn.com.git.cbs.engine.datamodel.TBL_Maintxdef;

/**
 * 表maintxdef的dao接口
 */
public interface TBL_MaintxdefDao {  				
	
	/**
	 * 根据Where条件查询一条数据
	 * Where条件字段：muid
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from MAINTXDEF
	 * 		where  1=1
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * </xmp>}
	 * @param maintxdef 表对象
	 * @return TBL_Maintxdef 查询结果
	 */
	public TBL_Maintxdef selMaintxdefByIdx1(TBL_Maintxdef maintxdef);
							
}
