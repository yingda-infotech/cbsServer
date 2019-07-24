/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_SubtxdefDao;
import cn.com.git.cbs.engine.datamodel.TBL_Subtxdef;

/**
 * 表subtxdef的dao实现类 
 */
@Repository 
public class TBL_SubtxdefDaoImpl extends GenericDAO<TBL_Subtxdef> implements TBL_SubtxdefDao{		
		
	@Override
	public TBL_Subtxdef selSubtxdefByIdx1(TBL_Subtxdef subtxdef){
			return (TBL_Subtxdef)super.selectOne("selSubtxdefByIdx1",subtxdef);
		}
		
}
