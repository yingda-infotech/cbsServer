/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.exception;

import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * 数据库异常
 * @author DengJia
 *
 */
public class DBException extends CbsRunTimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3698847633189428834L;
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	private final static String ERRCODE = "DB0001";

	public DBException(Exception e) {
		super(ERRCODE, ExceptionUtils.getErrorMessage(ERRCODE,
				ExceptionUtils.handleSQLException(e), e.getMessage()), e);
		LOGGER.error(this.getMessage(), e);
	}
}
