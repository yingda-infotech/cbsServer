/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_CmrspcodeDao;
import cn.com.git.cbs.engine.datamodel.TBL_Cmrspcode;

/**
 * 表cmrspcode的dao实现类 
 */
@Repository 
public class TBL_CmrspcodeDaoImpl extends GenericDAO<TBL_Cmrspcode> implements TBL_CmrspcodeDao{		
		
	@Override
	public TBL_Cmrspcode selCmrspcodeByIdx1(TBL_Cmrspcode cmrspcode){
			return (TBL_Cmrspcode)super.selectOne("selCmrspcodeByIdx1",cmrspcode);
		}
	
	@Override
	public List<TBL_Cmrspcode> selCmrspcodeListByIdx1(TBL_Cmrspcode cmrspcode){
			return super.select("selCmrspcodeListByIdx1",cmrspcode);
		}
	
	@Override
	public TBL_Cmrspcode selupdCmrspcodeByIdx1(TBL_Cmrspcode cmrspcode){
			return (TBL_Cmrspcode)super.selectOneForUpdate("selupdCmrspcodeByIdx1",cmrspcode);
		}
	
	@Override
	public int insCmrspcode(TBL_Cmrspcode cmrspcode){
			return super.insert("insCmrspcode",cmrspcode);
		}
	
	@Override
	public int updCmrspcodeByIdx1(TBL_Cmrspcode cmrspcode){
			return super.updateByPrimaryKey("updCmrspcodeByIdx1",cmrspcode);
		}
	
	@Override
	public int delCmrspcodeByIdx1(TBL_Cmrspcode cmrspcode){
			return super.deleteByPrimaryKey("delCmrspcodeByIdx1",cmrspcode);
		}
		
}
