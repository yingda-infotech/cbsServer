/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_PubdatadicDao;
import cn.com.git.cbs.engine.datamodel.TBL_Pubdatadic;

/**
 * 表pubdatadic的dao实现类 
 */
@Repository 
public class TBL_PubdatadicDaoImpl extends GenericDAO<TBL_Pubdatadic> implements TBL_PubdatadicDao{		
		
	@Override
	public TBL_Pubdatadic selPubdatadicByIdx1(TBL_Pubdatadic pubdatadic){
			return (TBL_Pubdatadic)super.selectOne("selPubdatadicByIdx1",pubdatadic);
		}
	
	@Override
	public List<TBL_Pubdatadic> selPubdatadicListByIdx1(TBL_Pubdatadic pubdatadic){
			return super.select("selPubdatadicListByIdx1",pubdatadic);
		}
	
	@Override
	public TBL_Pubdatadic selupdPubdatadicByIdx1(TBL_Pubdatadic pubdatadic){
			return (TBL_Pubdatadic)super.selectOneForUpdate("selupdPubdatadicByIdx1",pubdatadic);
		}
	
	@Override
	public int insPubdatadic(TBL_Pubdatadic pubdatadic){
			return super.insert("insPubdatadic",pubdatadic);
		}
	
	@Override
	public int updPubdatadicByIdx1(TBL_Pubdatadic pubdatadic){
			return super.updateByPrimaryKey("updPubdatadicByIdx1",pubdatadic);
		}
	
	@Override
	public int delPubdatadicByIdx1(TBL_Pubdatadic pubdatadic){
			return super.deleteByPrimaryKey("delPubdatadicByIdx1",pubdatadic);
		}
		
}
