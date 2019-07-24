/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_SuboutputDao;
import cn.com.git.cbs.engine.datamodel.TBL_Suboutput;

/**
 * 表suboutput的dao实现类 
 */
@Repository 
public class TBL_SuboutputDaoImpl extends GenericDAO<TBL_Suboutput> implements TBL_SuboutputDao{		
		
	@Override
	public TBL_Suboutput selSuboutputByIdx1(TBL_Suboutput suboutput){
			return (TBL_Suboutput)super.selectOne("selSuboutputByIdx1",suboutput);
		}
	
	@Override
	public List<TBL_Suboutput> selSuboutputListByIdx1(TBL_Suboutput suboutput){
			return super.select("selSuboutputListByIdx1",suboutput);
		}
	
	@Override
	public TBL_Suboutput selupdSuboutputByIdx1(TBL_Suboutput suboutput){
			return (TBL_Suboutput)super.selectOneForUpdate("selupdSuboutputByIdx1",suboutput);
		}
	
	@Override
	public int insSuboutput(TBL_Suboutput suboutput){
			return super.insert("insSuboutput",suboutput);
		}
	
	@Override
	public int updSuboutputByIdx1(TBL_Suboutput suboutput){
			return super.updateByPrimaryKey("updSuboutputByIdx1",suboutput);
		}
	
	@Override
	public int delSuboutputByIdx1(TBL_Suboutput suboutput){
			return super.deleteByPrimaryKey("delSuboutputByIdx1",suboutput);
		}
		
}
