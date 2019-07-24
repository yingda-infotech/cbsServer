/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_CmdatadicDao;
import cn.com.git.cbs.engine.datamodel.TBL_Cmdatadic;

/**
 * 表cmdatadic的dao实现类 
 */
@Repository 
public class TBL_CmdatadicDaoImpl extends GenericDAO<TBL_Cmdatadic> implements TBL_CmdatadicDao{		
		
	@Override
	public List<TBL_Cmdatadic> selCmdatadicListByIdx1(TBL_Cmdatadic cmdatadic){
			return super.select("selCmdatadicListByIdx1",cmdatadic);
		}
	
	@Override
	public int insCmdatadic(TBL_Cmdatadic cmdatadic){
			return super.insert("insCmdatadic",cmdatadic);
		}
		
}
