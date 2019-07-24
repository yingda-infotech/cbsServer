/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.com.git.cbs.engine.dao.TBL_MaintxdefDao;
import cn.com.git.cbs.engine.dao.TBL_SubinputDao;
import cn.com.git.cbs.engine.dao.TBL_SuboutputDao;
import cn.com.git.cbs.engine.dao.TBL_SubtxdefDao;
import cn.com.git.cbs.engine.dao.TBL_TranflowDao;
import cn.com.git.cbs.engine.datamodel.TBL_Maintxdef;
import cn.com.git.cbs.engine.datamodel.TBL_Subinput;
import cn.com.git.cbs.engine.datamodel.TBL_Suboutput;
import cn.com.git.cbs.engine.datamodel.TBL_Subtxdef;
import cn.com.git.cbs.engine.datamodel.TBL_Tranflow;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * 主交易 配置 Service
 * 
 * @author DengJia
 *
 */
@Service
public class TranMainConfigService {
	@Autowired
	private TBL_SubinputDao subInputDao;
	@Autowired
	private TBL_MaintxdefDao mainTxDefDao;
	@Autowired
	private TBL_TranflowDao tranFlowDao;
	@Autowired
	private TBL_SubtxdefDao subTxDefDao;
	@Autowired
	private TBL_SuboutputDao subOutputDao;

	@Cacheable(value = "tx_def", key = "'cn.com.git.cbs.engine.service.TranMainConfigService.getSubInputListByTranCode#'+#tranCode")
	public List<TBL_Subinput> getSubInputListByTranCode(String tranCode) {
		TBL_Subinput param = new TBL_Subinput();
		param.setMuid(tranCode);
		List<TBL_Subinput> ret = subInputDao.selSubinputListByIdx1(param);
		return ret;
	}

	@Cacheable(value = "tx_def", key = "'cn.com.git.cbs.engine.service.TranMainConfigService.getSubOutputListByTranCode#'+#tranCode")
	public List<TBL_Suboutput> getSubOutputListByTranCode(String tranCode) {
		TBL_Suboutput param = new TBL_Suboutput();
		param.setMuid(tranCode);
		List<TBL_Suboutput> ret = subOutputDao.selSuboutputListByIdx1(param);
		return ret;
	}

	@Cacheable(value = "tx_def", key = "'cn.com.git.cbs.engine.service.TranMainConfigService.getMainTxDef#'+#tranCode")
	public TBL_Maintxdef getMainTxDef(String tranCode) {
		TBL_Maintxdef param = new TBL_Maintxdef();
		param.setMuid(tranCode);
		TBL_Maintxdef ret = mainTxDefDao.selMaintxdefByIdx1(param);
		if (ret == null) {
			throw ExceptionUtils.returnError("DB0002", null, "MainTxDef");
		}
		return ret;
	}

	@Cacheable(value = "tx_def", key = "'cn.com.git.cbs.engine.service.TranMainConfigService.getTranFlowListByTranCode#'+#tranCode")
	public List<TBL_Tranflow> getTranFlowListByTranCode(String tranCode) {
		TBL_Tranflow param = new TBL_Tranflow();
		param.setMuid(tranCode);
		List<TBL_Tranflow> ret = tranFlowDao.selTranflowListByIdx1(param);
		if (CollectionUtils.isEmpty(ret)) {
			throw ExceptionUtils.returnError("DB0002", null, "TranFlow");
		}
		return ret;
	}

	@Cacheable(value = "tx_def", key = "'cn.com.git.cbs.engine.service.TranMainConfigService.getSubTxDef#'+#wuid")
	public TBL_Subtxdef getSubTxDef(String wuid) {
		TBL_Subtxdef param = new TBL_Subtxdef();
		param.setWuid(wuid);
		TBL_Subtxdef ret = subTxDefDao.selSubtxdefByIdx1(param);
		if (ret == null) {
			throw ExceptionUtils.returnError("DB0002", null, "SubTxDef");
		}
		return ret;
	}

	@CacheEvict(cacheNames = { "tx_def" })
	public void reloadConfig() {
		
	}
}
