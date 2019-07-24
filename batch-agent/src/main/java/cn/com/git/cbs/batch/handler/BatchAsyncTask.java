package cn.com.git.cbs.batch.handler;

import java.util.concurrent.Callable;

import cn.com.git.cbs.batch.BatchTask;
import cn.com.git.cbs.batch.context.BatchDataHolder;
import cn.com.git.cbs.batch.message.BatchRequest;
import cn.com.git.cbs.batch.message.BatchResponse;
import cn.com.git.cbs.exception.CbsRunTimeException;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.SpringContextUtils;

/**
 * 批处理异步任务
 * 
 * @author DengJia
 *
 */

public class BatchAsyncTask implements Callable<BatchResponse> {
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	private BatchRequest request;

	public BatchAsyncTask(BatchRequest request) {
		this.request = request;
	}

	/**
	 * 调用异步任务
	 */
	@Override
	public BatchResponse call() throws Exception {
		BatchDataHolder.setDataObject(request);
		String beanName = request.getProgName();
		if (SpringContextUtils.containsBean(beanName)) {
			BatchTask batchBean = SpringContextUtils.getBean(beanName, BatchTask.class);
			BatchResponse ret = null;
			try {
				ret = batchBean.execute(request);
				ret.importObject(request);
			} catch (CbsRunTimeException e) {
				LOGGER.error(e);
				ret = new BatchResponse(request);
				ret.setRspCode(e.getRspCode());
				ret.setRspMsg(e.getMessage());
			} catch (Exception e) {
				LOGGER.error(e);
				ret = new BatchResponse(request);
				ret.setRspCode("BA9999");
				ret.setRspMsg(e.getMessage());
			}
			BatchDataHolder.remove();
			return ret;
		} else {
			BatchResponse ret = new BatchResponse(request);
			ret.setRspCode("BA9999");
			ret.setRspMsg(("ProgName=[" + beanName + "]的Bean未找到"));
			BatchDataHolder.remove();
			return ret;
		}

	}
}
