/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import java.util.List;

import cn.com.git.cbs.engine.datamodel.TBL_Cmrspcode;

/**
 * 表cmrspcode的dao接口
 */
public interface TBL_CmrspcodeDao {  				
	
	/**
	 * 根据Where条件查询一条数据
	 * Where条件字段：rspcode
	 * {@code
	 * <xmp>
	 * select
	 * 	<include refid="Base_Column_List" />
	 * 	from CMRSPCODE
	 * 	where  1=1
	 * 		<if test="rspcode!= null" >
	 * 			 AND RSPCODE = #{rspcode, jdbcType=CHAR}
	 * 		</if>
	 * </xmp>
	 * }
	 * @param cmrspcode 表对象
	 * @return TBL_Cmrspcode 查询结果
	 */
	public TBL_Cmrspcode selCmrspcodeByIdx1(TBL_Cmrspcode cmrspcode);
	
	/**
	 * 根据Where条件查询多条数据
	 * Where条件字段：rspcode,memo,stdrspcode
	 * 排序字段：
	 * {@code
	 * <xmp>
	 * select
	 * 	<include refid="Base_Column_List" />
	 * 	from CMRSPCODE
	 * 	where  1=1
	 * 		<if test="rspcode!= null" >
	 * 			 AND RSPCODE = #{rspcode, jdbcType=CHAR}
	 * 		</if>
	 * 		<if test="memo!= null" >
	 * 			 AND MEMO = #{memo, jdbcType=CHAR}
	 * 		</if>
	 * 		<if test="stdrspcode!= null" >
	 * 			 AND STDRSPCODE = #{stdrspcode, jdbcType=CHAR}
	 * 		</if>
	 * </xmp>
	 * }
	 * @param cmrspcode 表对象
	 * @return 查询结果
	 */
	public List<TBL_Cmrspcode> selCmrspcodeListByIdx1(TBL_Cmrspcode cmrspcode);
	
	/**
	 * 根据Where条件查询数据(forUpdate方式查询)
	 * Where条件字段：rspcode
	 * {@code
	 * <xmp>
	 * select
	 * 	<include refid="Base_Column_List" />
	 * 	from CMRSPCODE
	 * 	where  1=1
	 * 		<if test="rspcode!= null" >
	 * 			 AND RSPCODE = #{rspcode, jdbcType=CHAR}
	 * 		</if>
	 * 	for update
	 * </xmp>
	 * }
	 * @param cmrspcode 表对象
	 * @return TBL_Cmrspcode 查询结果
	 */
	public TBL_Cmrspcode selupdCmrspcodeByIdx1(TBL_Cmrspcode cmrspcode);
	
	/**
	 * 插入一条数据，所有字段都要赋值，不赋值的字段将置为null
	 * {@code<xmp>
	 * insert into CMRSPCODE
	 * 	(RSPCODE,MEMO,STDRSPCODE)
	 * 	values
	 * 	(#{rspcode,jdbcType=CHAR},#{memo,jdbcType=CHAR},#{stdrspcode,jdbcType=CHAR})
	 * </xmp>}
	 * @param cmrspcode 表对象
	 * @return int 返回结果
	 */
	public int insCmrspcode(TBL_Cmrspcode cmrspcode);
	
	/**
	 * 根据Where条件修改一条数据
	 * Where条件字段：rspcode
	 * {@code<xmp>
	 * update CMRSPCODE
	 * 	<set>
	 * 		<if test="rspcode!= null" >
	 * 			RSPCODE = #{rspcode, jdbcType=CHAR},
	 * 		</if>
	 * 		<if test="memo!= null" >
	 * 			MEMO = #{memo, jdbcType=CHAR},
	 * 		</if>
	 * 		<if test="stdrspcode!= null" >
	 * 			STDRSPCODE = #{stdrspcode, jdbcType=CHAR}
	 * 		</if>
	 * 	</set>
	 * 	where
	 * 		<if test="rspcode!= null" >
	 * 			 AND RSPCODE = #{rspcode, jdbcType=CHAR}
	 * 		</if>
	 * </xmp>}
	 * @param cmrspcode 表对象
	 * @return int 返回结果
	 */
	public int updCmrspcodeByIdx1(TBL_Cmrspcode cmrspcode);
	
	/**
	 * 根据Where条件删除一条记录
	 * Where条件字段：rspcode
	 * {@code<xmp>
	 * delete from CMRSPCODE
	 * 	where 1=1
	 * 		<if test="rspcode!= null" >
	 * 			 AND RSPCODE = #{rspcode, jdbcType=CHAR}
	 * 		</if>
	 * </xmp>}
	 * @param cmrspcode 表对象
	 * @return int 返回结果
	 */
	public int delCmrspcodeByIdx1(TBL_Cmrspcode cmrspcode);
							
}
