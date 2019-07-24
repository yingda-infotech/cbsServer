/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_MaintxdefDao;
import cn.com.git.cbs.engine.datamodel.TBL_Maintxdef;

/**
 * 表maintxdef的dao实现类 
 */
@Repository 
public class TBL_MaintxdefDaoImpl extends GenericDAO<TBL_Maintxdef> implements TBL_MaintxdefDao{		
		
	@Override
	public TBL_Maintxdef selMaintxdefByIdx1(TBL_Maintxdef maintxdef){
			return (TBL_Maintxdef)super.selectOne("selMaintxdefByIdx1",maintxdef);
		}
		
}
