/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import java.util.List;

import cn.com.git.cbs.engine.datamodel.TBL_Txoutput;

/**
 * 表txoutput的dao接口
 */
public interface TBL_TxoutputDao {  				
	
	/**
	 * 根据Where条件查询一条数据
	 * Where条件字段：muid,datadicname
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from TXOUTPUT
	 * 		where  1=1
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="datadicname!= null" >
	 * 				 AND DATADICNAME = #{datadicname, jdbcType=CHAR}
	 * 			</if>
	 * </xmp>}
	 * @param txoutput 表对象
	 * @return TBL_Txoutput 查询结果
	 */
	public TBL_Txoutput selTxoutputByIdx1(TBL_Txoutput txoutput);
	
	/**
	 * 根据Where条件查询多条数据
	 * Where条件字段：poolname,muid,puid,outtype,datadicname
	 * 排序字段：
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from TXOUTPUT
	 * 		where  1=1
	 * 			<if test="poolname!= null" >
	 * 				 AND POOLNAME = #{poolname, jdbcType=VARCHAR}
	 * 			</if>
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="puid!= null" >
	 * 				 AND PUID = #{puid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="outtype!= null" >
	 * 				 ANDOUTTYPE = #{outtype, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="datadicname!= null" >
	 * 				 AND DATADICNAME = #{datadicname, jdbcType=CHAR}
	 * 			</if>
	 * 		
	 * </xmp>}
	 * @param txoutput 表对象
	 * @return 查询结果
	 */
	public List<TBL_Txoutput> selTxoutputListByIdx1(TBL_Txoutput txoutput);
	
	/**
	 * 插入一条数据，所有字段都要赋值，不赋值的字段将置为null
	 * {@code<xmp>
	 * 	insert into TXOUTPUT
	 * 		(POOLNAME,MUID,PUID,
	 * 		OUTTYPE,DATADICNAME)
	 * 		values
	 * 		(#{poolname,jdbcType=VARCHAR},#{muid,jdbcType=CHAR},#{puid,jdbcType=CHAR},
	 * 		#{outtype,jdbcType=CHAR},#{datadicname,jdbcType=CHAR})
	 * </xmp>}
	 * @param txoutput 表对象
	 * @return int 返回结果
	 */
	public int insTxoutput(TBL_Txoutput txoutput);
							
}
