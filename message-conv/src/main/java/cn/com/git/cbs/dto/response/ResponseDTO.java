/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dto.response;

import cn.com.git.cbs.model.DataObject;

/**
 * 响应数据转换对象
 * @author DengJia
 *
 */
public interface ResponseDTO {

	/**
	 * 将DataObject对象编码为报文字符串
	 * @param obj DataObject对象
	 * @return 报文体字符串（JSON或XML格式）
	 */
	public String encode(DataObject obj);

}