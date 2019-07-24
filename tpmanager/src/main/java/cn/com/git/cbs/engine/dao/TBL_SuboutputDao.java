/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao;

import java.util.List;

import cn.com.git.cbs.engine.datamodel.TBL_Suboutput;

/**
 * 表suboutput的dao接口
 */
public interface TBL_SuboutputDao {  				
	
	/**
	 * 根据Where条件查询一条数据
	 * Where条件字段：muid,wumark,localname
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from SUBOUTPUT
	 * 		where  1=1
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="wumark!= null" >
	 * 				 AND WUMARK = #{wumark, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="localname!= null" >
	 * 				 AND LOCALNAME = #{localname, jdbcType=VARCHAR}
	 * 			</if>
	 * </xmp>}
	 * @param suboutput 表对象
	 * @return TBL_Suboutput 查询结果
	 */
	public TBL_Suboutput selSuboutputByIdx1(TBL_Suboutput suboutput);
	
	/**
	 * 根据Where条件查询多条数据
	 * Where条件字段：muid,wumark,poolwumark,poolname,mapmode,localname,fixvalue,datadicname
	 * 排序字段：muid,wumark,localname
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from SUBOUTPUT
	 * 		where  1=1
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="wumark!= null" >
	 * 				 AND WUMARK = #{wumark, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="poolwumark!= null" >
	 * 				 AND POOLWUMARK = #{poolwumark, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="poolname!= null" >
	 * 				 ANDPOOLNAME = #{poolname, jdbcType=VARCHAR}
	 * 			</if>
	 * 			<if test="mapmode!= null" >
	 * 				 AND MAPMODE = #{mapmode, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="localname!= null" >
	 * 				 AND LOCALNAME = #{localname, jdbcType=VARCHAR}
	 * 			</if>
	 * 			<if test="fixvalue!= null" >
	 * 				 AND FIXVALUE = #{fixvalue, jdbcType=VARCHAR}
	 * 			</if>
	 * 			<if test="datadicname!= null" >
	 * 				 ANDDATADICNAME = #{datadicname, jdbcType=CHAR}
	 * 			</if>
	 * 		 ORDER BY muid,wumark,localname
	 * </xmp>}
	 * @param suboutput 表对象
	 * @return 查询结果
	 */
	public List<TBL_Suboutput> selSuboutputListByIdx1(TBL_Suboutput suboutput);
	
	/**
	 * 根据Where条件查询数据(forUpdate方式查询)
	 * Where条件字段：muid,wumark,localname
	 * {@code<xmp>
	 * 	select
	 * 		<include refid="Base_Column_List" />
	 * 		from SUBOUTPUT
	 * 		where  1=1
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="wumark!= null" >
	 * 				 AND WUMARK = #{wumark, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="localname!= null" >
	 * 				 AND LOCALNAME = #{localname, jdbcType=VARCHAR}
	 * 			</if>
	 * 		for update
	 * </xmp>}
	 * @param suboutput 表对象
	 * @return TBL_Suboutput 查询结果
	 */
	public TBL_Suboutput selupdSuboutputByIdx1(TBL_Suboutput suboutput);
	
	/**
	 * 插入一条数据，所有字段都要赋值，不赋值的字段将置为null
	 * {@code<xmp>
	 * 	insert into SUBOUTPUT
	 * 		(MUID,WUMARK,POOLWUMARK,
	 * 		POOLNAME,MAPMODE,LOCALNAME,FIXVALUE,
	 * 		DATADICNAME)
	 * 		values
	 * 		(#{muid,jdbcType=CHAR},#{wumark,jdbcType=CHAR},#{poolwumark,jdbcType=CHAR},
	 * 		#{poolname,jdbcType=VARCHAR},#{mapmode,jdbcType=CHAR},#{localname,jdbcType=VARCHAR},#{fixvalue,jdbcType=VARCHAR},
	 * 		#{datadicname,jdbcType=CHAR})
	 * </xmp>}
	 * @param suboutput 表对象
	 * @return int 返回结果
	 */
	public int insSuboutput(TBL_Suboutput suboutput);
	
	/**
	 * 根据Where条件修改一条数据
	 * Where条件字段：muid,wumark,localname
	 * {@code<xmp>
	 * 	update SUBOUTPUT
	 * 		<set>
	 * 			<if test="muid!= null" >
	 * 				MUID = #{muid, jdbcType=CHAR},
	 * 			</if>
	 * 			<if test="wumark!= null" >
	 * 				WUMARK = #{wumark, jdbcType=CHAR},
	 * 			</if>
	 * 			<if test="poolwumark!= null" >
	 * 				POOLWUMARK = #{poolwumark, jdbcType=CHAR},
	 * 			</if>
	 * 			<if test="poolname!= null" >
	 * 				POOLNAME = #{poolname, jdbcType=VARCHAR},
	 * 			</if>
	 * 			<if test="mapmode!= null" >
	 * 				MAPMODE = #{mapmode, jdbcType=CHAR},
	 * 			</if>
	 * 			<if test="localname!= null" >
	 * 				LOCALNAME = #{localname, jdbcType=VARCHAR},
	 * 			</if>
	 * 			<if test="fixvalue!= null" >
	 * 				FIXVALUE = #{fixvalue, jdbcType=VARCHAR},
	 * 			</if>
	 * 			<if test="datadicname!= null" >
	 * 				DATADICNAME = #{datadicname, jdbcType=CHAR}
	 * 			</if>
	 * 		</set>
	 * 		where
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="wumark!= null" >
	 * 				 AND WUMARK = #{wumark, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="localname!= null" >
	 * 				 AND LOCALNAME = #{localname, jdbcType=VARCHAR}
	 * 			</if>
	 * </xmp>}
	 * @param suboutput 表对象
	 * @return int 返回结果
	 */
	public int updSuboutputByIdx1(TBL_Suboutput suboutput);
	
	/**
	 * 根据Where条件删除一条记录
	 * Where条件字段：muid,wumark,localname
	 * {@code<xmp>
	 * 	delete from SUBOUTPUT
	 * 		where 1=1
	 * 			<if test="muid!= null" >
	 * 				 AND MUID = #{muid, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="wumark!= null" >
	 * 				 AND WUMARK = #{wumark, jdbcType=CHAR}
	 * 			</if>
	 * 			<if test="localname!= null" >
	 * 				 AND LOCALNAME = #{localname, jdbcType=VARCHAR}
	 * 			</if>
	 * </xmp>}
	 * @param suboutput 表对象
	 * @return int 返回结果
	 */
	public int delSuboutputByIdx1(TBL_Suboutput suboutput);
							
}
