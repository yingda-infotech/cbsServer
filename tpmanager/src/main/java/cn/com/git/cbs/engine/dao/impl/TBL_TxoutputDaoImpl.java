/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.git.cbs.dao.GenericDAO;
import cn.com.git.cbs.engine.dao.TBL_TxoutputDao;
import cn.com.git.cbs.engine.datamodel.TBL_Txoutput;

/**
 * 表txoutput的dao实现类 
 */
@Repository 
public class TBL_TxoutputDaoImpl extends GenericDAO<TBL_Txoutput> implements TBL_TxoutputDao{		
		
	@Override
	public TBL_Txoutput selTxoutputByIdx1(TBL_Txoutput txoutput){
			return (TBL_Txoutput)super.selectOne("selTxoutputByIdx1",txoutput);
		}
	
	@Override
	public List<TBL_Txoutput> selTxoutputListByIdx1(TBL_Txoutput txoutput){
			return super.select("selTxoutputListByIdx1",txoutput);
		}
	
	@Override
	public int insTxoutput(TBL_Txoutput txoutput){
			return super.insert("insTxoutput",txoutput);
		}
		
}
