package cn.com.git.cbs.engine;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.engine.datamodel.TBL_Maintxdef;
import cn.com.git.cbs.engine.datamodel.TBL_Subinput;
import cn.com.git.cbs.engine.datamodel.TBL_Suboutput;
import cn.com.git.cbs.engine.datamodel.TBL_Subtxdef;
import cn.com.git.cbs.engine.datamodel.TBL_Tranflow;
import cn.com.git.cbs.engine.service.TranMainConfigService;
import cn.com.git.cbs.exception.CbsRunTimeException;
import cn.com.git.cbs.expression.FelEngineUtil;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.ExceptionUtils;
import cn.com.git.cbs.platform.utils.SpringContextUtils;
import cn.com.git.cbs.tpmanager.SubTransaction;
import cn.com.git.cbs.tpmanager.TranMain;
import cn.com.git.cbs.tpmanager.TranMainCtx;

/***
 * 主交易类
 * 
 * @author DengJia
 *
 */
@Component
@Qualifier("tranMainImpl")
public class TranMainImpl implements TranMain {
	@Autowired
	private TranMainConfigService service;
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	private final static String ERR_RSP_CODE = "PL9999";
	@Autowired
	@Qualifier("txDef")
	private TransactionDefinition txDef;
	@Autowired
	private PlatformTransactionManager sxm; // 查询事物
	@Autowired
	private PlatformTransactionManager txm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.git.cbs.engine.TranMainInterface#execute(cn.com.git.cbs.model.
	 * DataObject)
	 */
	@Override
	public DataObject execute(DataObject inParam) {
		DataPoolImpl datapool = new DataPoolImpl();
		datapool.setDataObject(inParam);
		processExcecution(datapool);
		return datapool.getDataObject();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void processExcecution(DataPoolImpl datapool) {
		TranMainCtx.setDataPool(datapool);
		String tranCode = datapool.getValue("trancode", false);
		TBL_Maintxdef mainTxDef = null; // 主交易定义
		List<TBL_Tranflow> tranFlows = null; // 子交易联动流程
		List<TBL_Subinput> subInputs = null; // 子交易输入映射定义表
		List<TBL_Suboutput> subOutputs = null; // 子交易输出映射定义表
		List<TBL_Tranflow> normalTranFlows = null;
		List<TBL_Tranflow> exceptTranFlows = null;
		try {
			// 启动查询事务
			TransactionStatus selTxStatus = sxm.getTransaction(txDef);
			try {
				LOGGER.debug("<< Begin Main Service >>");
				// 2、交易初始化
				LOGGER.debug("<< Begin service init>>");

				mainTxDef = service.getMainTxDef(tranCode);
				tranFlows = service.getTranFlowListByTranCode(tranCode);
				subInputs = service.getSubInputListByTranCode(tranCode);
				subOutputs = service.getSubOutputListByTranCode(tranCode);
				normalTranFlows = (List<TBL_Tranflow>) CollectionUtils.select(tranFlows, new NormalTranFlowSelector());
				exceptTranFlows = (List<TBL_Tranflow>) CollectionUtils.subtract(tranFlows, normalTranFlows);
				// 交易初始化结束
				sxm.commit(selTxStatus);

				// 3、初始化DataCommon数据
				// 初始化DataCommon数据结束
				datapool.setValue("rspcode", "000000", false);
			} catch (Exception e) {
				packMessage(e, datapool);
				sxm.rollback(selTxStatus);
				return;
			}
			// 启动事务
			TransactionStatus normalTxStatus = txm.getTransaction(txDef);
			try {
				// 4、主交易前处理
				LOGGER.debug("<< Begin invoke>>");
				beforeMainSrvRun();
				// 5、调度正常子交易
				try {
					runSubSrv(normalTranFlows, subInputs, subOutputs, datapool);
				} catch (Exception e) {
					txm.rollback(normalTxStatus);
					TransactionStatus exceptionTx = txm.getTransaction(txDef);
					try {
						// 调用异常子交易流程
						runSubSrv(exceptTranFlows, subInputs, subOutputs, datapool);
						LOGGER.debug("return msg");
						txm.commit(exceptionTx);
						return;
					} catch (Exception ex) {
						txm.rollback(exceptionTx);
						packMessage(ex, datapool);
						return;
					}
				}
				afterMainSrvRun();
				txm.commit(normalTxStatus);
				LOGGER.debug("return msg");
				return;
			} catch (Exception e) {
				txm.rollback(normalTxStatus);
				packMessage(e, datapool);
				return;
			}
		} catch (Exception e) {
			packMessage(e, datapool);
			return;
		} finally {
			// 清理上下文
			TranMainCtx.remove();
			LOGGER.debug("<< End Main Service >>");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.git.cbs.engine.TranMainInterface#executeBatch(cn.com.git.cbs.model.
	 * DataObject)
	 */
	@Override
	public void executeBatch(DataObject inParam) {
		DataPoolImpl datapool = new DataPoolImpl();
		datapool.setDataObject(inParam);
		processExcecution(datapool);
	}

	/**
	 * 生成报错时的返回报文
	 * 
	 * @param pool
	 * @param rspCode
	 * @param rspMsg
	 * @return
	 */
	private void packMessage(Exception e, DataPoolImpl datapool) {
		LOGGER.error(e);
		if (e instanceof CbsRunTimeException) {
			datapool.setValue("rspcode", ((CbsRunTimeException) e).getRspCode(), false);
			datapool.setValue("rspmsg", ((CbsRunTimeException) e).getMessage(), false);
		} else {
			datapool.setValue("rspcode", ERR_RSP_CODE, false);
			datapool.setValue("rspmsg", ExceptionUtils.getErrorMessage(ERR_RSP_CODE, e.getMessage()), false);
		}
	}

	/**
	 * 主交易前处理
	 */
	private void beforeMainSrvRun() {
		// TODO：待实现
	}

	/**
	 * 子交易处理
	 */
	@SuppressWarnings("unchecked")
	private void runSubSrv(List<TBL_Tranflow> subSrvList, List<TBL_Subinput> subInputs, List<TBL_Suboutput> subOutputs,
			DataPoolImpl datapool) {
		for (TBL_Tranflow tblTranFlow : subSrvList) {
			LOGGER.debug("子交易进入条件=[%s]", tblTranFlow.getEntrycond());
			// 是否需要调用子交易
			boolean callFlag = false;
			if (StringUtils.isBlank(tblTranFlow.getEntrycond())) {
				// 如果为空则无条件调用
				callFlag = true;
			} else {
				callFlag = FelEngineUtil.evalBoolean(tblTranFlow.getEntrycond(), datapool.getDataObject().getData());
			}
			LOGGER.debug("子交易进入结果=[%s]", callFlag);
			if (callFlag) {
				datapool.setWumark(tblTranFlow.getWumark());
				/* 查询子交易信息定义表，取子交易信息 */
				TBL_Subtxdef curSubTxDef = service.getSubTxDef(tblTranFlow.getWuid());
				// 该子交易的运行参数
				List<TBL_Subinput> curSubInputs = (List<TBL_Subinput>) CollectionUtils.select(subInputs,
						new SubInputSelector(tblTranFlow.getMuid(), tblTranFlow.getWumark()));
				// 该子交易的WuMark不能为空
				if (StringUtils.isBlank(tblTranFlow.getWumark())) {
					LOGGER.error("WuID=[%s]的子交易WuMark为空，交易中断", tblTranFlow.getWuid());
					throw ExceptionUtils.returnError("PL3001", null, tblTranFlow.getWuid());
				}
				// 当前子交易运行前处理
				beforeSubSrvRun(curSubInputs, datapool);
				callSubSrvRun(curSubTxDef.getWufunc());

				// TODO:需要修改为OUTPUT类型
				List<TBL_Suboutput> curSubOutputs = (List<TBL_Suboutput>) CollectionUtils.select(subOutputs,
						new SubOutputSelector(tblTranFlow.getMuid(), tblTranFlow.getWumark()));
				// 子交易运行后处理
				afterSubSrvRun(curSubOutputs, datapool);
			}
		}
	}

	/***
	 * 子交易运行前处理，根据子交易输入参数映射信息，从数据池中获取数值，计算或转换后置入数据池中
	 * 
	 * @param curSubInputs
	 *            子交易输入参数映射信息
	 * @param datapool
	 *            数据缓冲池
	 */
	private void beforeSubSrvRun(List<TBL_Subinput> curSubInputs, DataPoolImpl datapool) {
		// 从subInput中取出信息，然后赋值到datapool中
		for (TBL_Subinput subInput : curSubInputs) {
			// 当前wumark
			String curParamKey = StringUtils.join(subInput.getWumark(), ".", subInput.getLocalname());
			String value = "";
			switch (subInput.getMapmode()) {
			// 0:从DATAPOOL中获取数
			case "0":
				// 0为 wumark.localname=poolwumark+poolname
				String lookKey = subInput.getPoolname();
				if (StringUtils.isNoneBlank(subInput.getPoolwumark()) && !"0".equals(subInput.getPoolwumark())) {
					lookKey = StringUtils.join(subInput.getPoolwumark(), ".", lookKey);
				}

				Object poolValue = datapool.getDataObject().get(lookKey);
				if (poolValue instanceof List) {
					LOGGER.debug("从数据池中获取列表数值, key=[%s]", lookKey);
					datapool.getDataObject().set(curParamKey, poolValue);
					return;
				}

				value = datapool.getValue(lookKey, false);
				LOGGER.debug("从数据池中获取数值, key=[%s],value=[%s]", lookKey, value);
				break;
			// 1:获取常量数据(FixValue)
			case "1":
				// 1为fixvalue
				value = subInput.getFixvalue();
				LOGGER.debug("获取固定数值, value=[%s]", value);
				break;
			// 2:通过算术表达式获取数据(FixValue)
			case "2":
				// 3:通过表达式赋值
			case "3":
				// 2为表达式，3为三元表达式，表达式均从fixvalue中取出
				String formula = subInput.getFixvalue();
				value = FelEngineUtil.evalString(formula, datapool.getDataObject().getData());
				LOGGER.debug("根据公式计算数值, forumla=[%s],value=[%s]", formula, value);
				break;
			default:
				throw ExceptionUtils.returnError("PL3002", null, subInput.getMuid(), subInput.getWumark(),
						subInput.getLocalname(), subInput.getMapmode());
			}
			datapool.setValue(curParamKey, value, false);
		}
	}

	/***
	 * 子交易调用处理
	 * 
	 * @param beanId
	 *            子交易BeanID
	 * @exception CbsRunTimeException
	 *                errorCode=PL3003
	 */
	private void callSubSrvRun(String beanId) {
		LOGGER.debug("调用子交易=[%s]", beanId);
		SubTransaction subTransaction = SpringContextUtils.getBean(beanId, SubTransaction.class);
		if (subTransaction == null) {
			throw ExceptionUtils.returnError("PL3003", null, beanId);
		}

		subTransaction.execute(TranMainCtx.getDataPool());
		LOGGER.debug("调用子交易结束=[%s]", beanId);
	}

	/***
	 * 子交易运行后处理，根据子交易输出参数映射信息，从数据池中获取数值，计算或转换后置入数据池中
	 * 
	 * @param curSubOutputs
	 *            子交易输出参数映射信息
	 * @param datapool
	 *            数据缓冲池
	 */
	private void afterSubSrvRun(List<TBL_Suboutput> curSubOutputs, DataPoolImpl datapool) {
		// 从subOutput中取出信息，然后赋值到datapool中
		LOGGER.debug("进入afterSubSrvRun");
		for (TBL_Suboutput subOutput : curSubOutputs) {
			// 支持向公共区回写
			String lookKey = subOutput.getPoolname();
			LOGGER.debug("获取subOutput, lookKey=[%s]", lookKey);
			if (StringUtils.isNoneBlank(subOutput.getPoolwumark()) && !"0".equals(subOutput.getPoolwumark())) {
				lookKey = StringUtils.join(subOutput.getPoolwumark(), ".", lookKey);
			}

			String value = "";
			switch (subOutput.getMapmode()) {
			case "0":
				// 0为 wumark.localname=poolwumark+poolname
				String curParamKey = StringUtils.join(subOutput.getWumark(), ".", subOutput.getLocalname());
				Object poolValue = datapool.getDataObject().get(curParamKey);
				if (poolValue instanceof List) {
					LOGGER.debug("从数据池中获取列表数值, key=[%s]", curParamKey);
					datapool.getDataObject().set(lookKey, poolValue);
					return;
				}
				value = datapool.getValue(curParamKey, false);
				LOGGER.debug("从数据池中获取数值, key=[%s],value=[%s]", curParamKey, value);
				break;
			case "1":
				// 1为fixvalue
				value = subOutput.getFixvalue();
				LOGGER.debug("获取固定数值, value=[%s]", value);
				break;
			case "2":
			case "3":
				// 2为表达式，3为三元表达式，表达式均从fixvalue中取出
				String formula = subOutput.getFixvalue();
				value = FelEngineUtil.evalString(formula, datapool.getDataObject().getData());
				LOGGER.debug("根据公式计算数值, forumla=[%s],value=[%s]", formula, value);
				break;
			default:
				throw ExceptionUtils.returnError("PL3004", null, subOutput.getMuid(), subOutput.getWumark(),
						subOutput.getLocalname(), subOutput.getMapmode());
			}
			datapool.setValue(lookKey, value, false);
		}

	}

	private void afterMainSrvRun() {
		/*
		 * 7.1：产生授权； 7.2：收费账务处理 7.3：消息处理 7.4：事务的控制
		 */
	}

	/***
	 * 子交易输入参数选择器
	 * 
	 * @author DengJia
	 *
	 */
	private class SubInputSelector implements Predicate {

		private String wumark;
		private String muid;

		/***
		 * 构造器
		 * 
		 * @param muid
		 *            交易代码
		 * @param wumark
		 *            子交易标识
		 */
		public SubInputSelector(final String muid, final String wumark) {
			this.wumark = wumark;
			this.muid = muid;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
		 */
		@Override
		public boolean evaluate(Object arg0) {
			TBL_Subinput obj = (TBL_Subinput) arg0;
			return (muid.equals(obj.getMuid()) && wumark.equals(obj.getWumark()));
		}

	}

	/***
	 * 子交易输出参数选择器
	 * 
	 * @author DengJia
	 *
	 */
	private class SubOutputSelector implements Predicate {

		private String wumark;
		private String muid;

		/***
		 * 构造器
		 * 
		 * @param muid
		 *            交易代码
		 * @param wumark
		 *            子交易标识
		 */
		public SubOutputSelector(final String muid, final String wumark) {
			this.wumark = wumark;
			this.muid = muid;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
		 */
		@Override
		public boolean evaluate(Object arg0) {
			TBL_Suboutput obj = (TBL_Suboutput) arg0;
			return (muid.equals(obj.getMuid()) && wumark.equals(obj.getWumark()));
		}

	}

	/***
	 * 正常子交易联动选择器
	 * 
	 * @author DengJia
	 *
	 */
	private class NormalTranFlowSelector implements Predicate {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
		 */
		@Override
		public boolean evaluate(Object arg0) {
			TBL_Tranflow obj = (TBL_Tranflow) arg0;
			if (obj != null && "00".equals(obj.getParam1())) {
				return true;
			}
			return false;
		}
	}
}

@SpringBootConfiguration
class TransactionConf {
	@Bean("txDef")
	public TransactionDefinition getTransactionDef() {
		DefaultTransactionDefinition ret = new DefaultTransactionDefinition();
		ret.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
		return ret;
	}
}
