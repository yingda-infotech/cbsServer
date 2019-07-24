/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dto.request;

import cn.com.git.cbs.model.DataObject;

/**
 * 联机交易 数据转换对象接口
 * @author DengJia
 *
 */
public interface RequestDTO {

	public DataObject decode(String requestMsg);

}