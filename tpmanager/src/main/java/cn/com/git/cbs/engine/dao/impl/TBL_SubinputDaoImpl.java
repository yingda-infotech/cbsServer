/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_SubinputDao;
import cn.com.git.cbs.engine.datamodel.TBL_Subinput;

/**
 * 表subinput的dao实现类 
 */
@Repository 
public class TBL_SubinputDaoImpl extends GenericDAO<TBL_Subinput> implements TBL_SubinputDao{		
		
	@Override
	public List<TBL_Subinput> selSubinputListByIdx1(TBL_Subinput subinput){
			return super.select("selSubinputListByIdx1",subinput);
		}
		
}
