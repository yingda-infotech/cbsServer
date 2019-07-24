/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import java.util.List;

import cn.com.git.cbs.engine.datamodel.TBL_Cmdatadic;

/**
 * 表cmdatadic的dao接口
 */
public interface TBL_CmdatadicDao {  				
	
	/**
	 * 根据Where条件查询多条数据
	 * Where条件字段：dictcode,dictname,dicttype,dictlen,declen
	 * 排序字段：
	 * {@code
	 * <xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from CMDATADIC
	 * 		where  1=1
	 * 			<if test="dictcode!= null" >
	 * 				 AND DICTCODE = #{dictcode, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dictname!= null" >
	 * 				 AND DICTNAME = #{dictname, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dicttype!= null" >
	 * 				 AND DICTTYPE = #{dicttype, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dictlen!= null" >
	 * 				 ANDDICTLEN = #{dictlen, jdbcType=INTEGER}
	 * 			</if>
	 * 			<if test="declen!= null" >
	 * 				 AND DECLEN = #{declen, jdbcType=INTEGER}
	 * 			</if>
	 * </xmp>
	 * }
	 * @param cmdatadic  表对象
	 * @return 查询结果列表
	 */
	public List<TBL_Cmdatadic> selCmdatadicListByIdx1(TBL_Cmdatadic cmdatadic);
	
	/**
	 * 插入一条数据，所有字段都要赋值，不赋值的字段将置为null
	 * {@code
	 * <xmp>
	 * 	insert into CMDATADIC
	 * 		(DICTCODE,DICTNAME,DICTTYPE,
	 * 		DICTLEN,DECLEN)
	 * 		values
	 * 		(#{dictcode,jdbcType=CHAR},#{dictname,jdbcType=CHAR},#{dicttype,jdbcType=CHAR},
	 * 		#{dictlen,jdbcType=INTEGER},#{declen,jdbcType=INTEGER})
	 * </xmp>
	 * }
	 * @param cmdatadic 表对象
	 * @return int 返回结果
	 */
	public int insCmdatadic(TBL_Cmdatadic cmdatadic);
							
}
