/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import java.util.List;

import cn.com.git.cbs.engine.datamodel.TBL_Pubdatadic;

/**
 * 表pubdatadic的dao接口
 */
public interface TBL_PubdatadicDao {  				
	
	/**
	 * 根据Where条件查询一条数据
	 * Where条件字段：dictcode
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from PUBDATADIC
	 * 		where  1=1
	 * 			<if test="dictcode!= null" >
	 * 				 AND DICTCODE = #{dictcode, jdbcType=CHAR}
	 * 			</if>
	 * </xmp>}
	 * @param pubdatadic 表对象
	 * @return TBL_Pubdatadic 查询结果
	 */
	public TBL_Pubdatadic selPubdatadicByIdx1(TBL_Pubdatadic pubdatadic);
	
	/**
	 * 根据Where条件查询多条数据
	 * Where条件字段：dictcode,dictname,dictmemo,dicttype,dictlen,declen,flag,lowcode
	 * 排序字段：
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from PUBDATADIC
	 * 		where  1=1
	 * 			<if test="dictcode!= null" >
	 * 				 AND DICTCODE = #{dictcode, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dictname!= null" >
	 * 				 AND DICTNAME = #{dictname, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dictmemo!= null" >
	 * 				 AND DICTMEMO = #{dictmemo, jdbcType=VARCHAR}
	 * 			</if>
	 * 			<if test="dicttype!= null" >
	 * 				 ANDDICTTYPE = #{dicttype, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dictlen!= null" >
	 * 				 AND DICTLEN = #{dictlen, jdbcType=INTEGER}
	 * 			</if>
	 * 			<if test="declen!= null" >
	 * 				 AND DECLEN = #{declen, jdbcType=INTEGER}
	 * 			</if>
	 * 			<if test="flag!= null" >
	 * 				 AND FLAG = #{flag, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="lowcode!= null" >
	 * 				 ANDLOWCODE = #{lowcode, jdbcType=VARCHAR}
	 * 			</if>
	 * 		
	 * </xmp>}
	 * @param pubdatadic 表对象
	 * @return 查询结果
	 */
	public List<TBL_Pubdatadic> selPubdatadicListByIdx1(TBL_Pubdatadic pubdatadic);
	
	/**
	 * 根据Where条件查询数据(forUpdate方式查询)
	 * Where条件字段：dictcode
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from PUBDATADIC
	 * 		where  1=1
	 * 			<if test="dictcode!= null" >
	 * 				 AND DICTCODE = #{dictcode, jdbcType=CHAR}
	 * 			</if>
	 * 		for update
	 * </xmp>}
	 * @param pubdatadic 表对象
	 * @return TBL_Pubdatadic 查询结果
	 */
	public TBL_Pubdatadic selupdPubdatadicByIdx1(TBL_Pubdatadic pubdatadic);
	
	/**
	 * 插入一条数据，所有字段都要赋值，不赋值的字段将置为null
	 * {@code<xmp>
	 * 	insert into PUBDATADIC
	 * 		(DICTCODE,DICTNAME,DICTMEMO,
	 * 		DICTTYPE,DICTLEN,DECLEN,FLAG,
	 * 		LOWCODE)
	 * 		values
	 * 		(#{dictcode,jdbcType=CHAR},#{dictname,jdbcType=CHAR},#{dictmemo,jdbcType=VARCHAR},
	 * 		#{dicttype,jdbcType=CHAR},#{dictlen,jdbcType=INTEGER},#{declen,jdbcType=INTEGER},#{flag,jdbcType=CHAR},
	 * 		#{lowcode,jdbcType=VARCHAR})
	 * </xmp>}
	 * @param pubdatadic 表对象
	 * @return int 返回结果
	 */
	public int insPubdatadic(TBL_Pubdatadic pubdatadic);
	
	/**
	 * 根据Where条件修改一条数据
	 * Where条件字段：dictcode,dictname,dictmemo,dicttype,dictlen,declen,flag,lowcode
	 * {@code<xmp>
	 * 	update PUBDATADIC
	 * 		<set>
	 * 			<if test="dictcode!= null" >
	 * 				DICTCODE = #{dictcode, jdbcType=CHAR},
	 * 			</if>
	 * 			<if test="dictname!= null" >
	 * 				DICTNAME = #{dictname, jdbcType=CHAR},
	 * 			</if>
	 * 			<if test="dictmemo!= null" >
	 * 				DICTMEMO = #{dictmemo, jdbcType=VARCHAR},
	 * 			</if>
	 * 			<if test="dicttype!= null" >
	 * 				DICTTYPE = #{dicttype, jdbcType=CHAR},
	 * 			</if>
	 * 			<if test="dictlen!= null" >
	 * 				DICTLEN = #{dictlen, jdbcType=INTEGER},
	 * 			</if>
	 * 			<if test="declen!= null" >
	 * 				DECLEN = #{declen, jdbcType=INTEGER},
	 * 			</if>
	 * 			<if test="flag!= null" >
	 * 				FLAG = #{flag, jdbcType=CHAR},
	 * 			</if>
	 * 			<if test="lowcode!= null" >
	 * 				LOWCODE = #{lowcode, jdbcType=VARCHAR}
	 * 			</if>
	 * 		</set>
	 * 		where
	 * 			<if test="dictcode!= null" >
	 * 				 AND DICTCODE = #{dictcode, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dictname!= null" >
	 * 				 AND DICTNAME = #{dictname, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dictmemo!= null" >
	 * 				 AND DICTMEMO = #{dictmemo, jdbcType=VARCHAR}
	 * 			</if>
	 * 			<if test="dicttype!= null" >
	 * 				 ANDDICTTYPE = #{dicttype, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="dictlen!= null" >
	 * 				 AND DICTLEN = #{dictlen, jdbcType=INTEGER}
	 * 			</if>
	 * 			<if test="declen!= null" >
	 * 				 AND DECLEN = #{declen, jdbcType=INTEGER}
	 * 			</if>
	 * 			<if test="flag!= null" >
	 * 				 AND FLAG = #{flag, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="lowcode!= null" >
	 * 				 ANDLOWCODE = #{lowcode, jdbcType=VARCHAR}
	 * 			</if>
	 * </xmp>}
	 * @param pubdatadic 表对象
	 * @return int 返回结果
	 */
	public int updPubdatadicByIdx1(TBL_Pubdatadic pubdatadic);
	
	/**
	 * 根据Where条件删除一条记录
	 * Where条件字段：dictcode
	 * {@code<xmp>
	 * 	delete from PUBDATADIC
	 * 		where 1=1
	 * 			<if test="dictcode!= null" >
	 * 				 AND DICTCODE = #{dictcode, jdbcType=CHAR}
	 * 			</if>
	 * </xmp>}
	 * @param pubdatadic 表对象
	 * @return int 返回结果
	 */
	public int delPubdatadicByIdx1(TBL_Pubdatadic pubdatadic);
							
}
