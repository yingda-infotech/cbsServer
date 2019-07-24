/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_TranflowDao;
import cn.com.git.cbs.engine.datamodel.TBL_Tranflow;

/**
 * 表tranflow的dao实现类 
 */
@Repository 
public class TBL_TranflowDaoImpl extends GenericDAO<TBL_Tranflow> implements TBL_TranflowDao{		
		
	@Override
	public List<TBL_Tranflow> selTranflowListByIdx1(TBL_Tranflow tranflow){
			return super.select("selTranflowListByIdx1",tranflow);
		}
		
}
