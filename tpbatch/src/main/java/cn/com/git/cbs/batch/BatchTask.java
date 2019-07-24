/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.batch;

import cn.com.git.cbs.batch.message.BatchRequest;
import cn.com.git.cbs.batch.message.BatchResponse;

/**
 * 批处理任务接口
 * @author DengJia
 *
 */
public interface BatchTask {
	
	/**
	 * 执行批处理任务
	 * @param request TODO
	 * @return 批处理执行结果
	 */
	public BatchResponse execute(BatchRequest request);
}
