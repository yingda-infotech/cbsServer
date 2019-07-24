/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.platform.utils;

import java.sql.SQLException;
import org.springframework.dao.DataAccessException;

import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.exception.CbsRunTimeException;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.model.ReturnObj;

/**
 * 异常操作类
 * @author DengJia
 *
 */
public final class ExceptionUtils {
	private final static PlatformLogger LOGGER = PlatformLogger.create();

	private ExceptionUtils() {

	}

	/**
	 * 从操作数据库发生异常的exception中获取异常码，如果获取失败，返回-9999
	 * @param e 异常
	 * @return SQL异常码，如果是非SQL异常则返回-9999
	 */
	public static int handleSQLException(Exception e) {
		if (e instanceof DataAccessException) {
			if (e.getCause() instanceof DataAccessException) {
				e = (DataAccessException) e.getCause();
			}
			if (e.getCause() instanceof SQLException) {
				int errCode = ((SQLException) e.getCause()).getErrorCode();
				if (errCode == 0) {
					return ReturnObj.FAILURE;
				} else {
					return -errCode;
				}
			}
		}
		return ReturnObj.FAILURE;
	}

	/**
	 * 返回异常信息，把异常的参数按照定义的格式组装成异常信息返回
	 * @param errorCode 错误代码
	 * @param args 异常信息的参数
	 * @return CbsRunTimeException
	 */
	public static CbsRunTimeException returnError(String errorCode,
			Object... args) {
		return returnError(errorCode, null, args);
	}

	/**
	 * 返回异常信息，把异常的参数按照定义的格式组装成异常信息返回
	 * @param errorCode 异常码
	 * @param t Throwable异常
	 * @param args 异常消息的参数
	 * @return CbsRunTimeException
	 */
	public static CbsRunTimeException returnError(String errorCode,
			Throwable t, Object... args) {
		if (StringUtils.isBlank(errorCode)) {
			LOGGER.warn("传入的错误码不能为空！");
			errorCode = "PL9999";
		}
		String errorMsg = getErrorMessage(errorCode, args);
		LOGGER.error(errorMsg, t);
		return new CbsRunTimeException(errorCode, errorMsg, t);
	}

	/**
	 * 从Property中获取异常码对应的异常信息
	 * @param errorCode 异常码
	 * @param args 异常消息的参数
	 * @return 异常信息
	 */
	public static String getErrorMessage(String errorCode, Object... args) {
		String errorMsg = "未知异常：";
		String pattern = null;
//		pattern = SpringContextUtils.getBean("exceptionCodeProperties",
//				Properties.class).getProperty(errorCode);
		pattern = ConfigUtils.getErrorProperty(errorCode);

		if (StringUtils.isNotBlank(pattern)) {
			errorMsg = StringUtils.format(pattern, args);
		}
		return errorMsg;
	}
}
