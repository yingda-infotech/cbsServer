/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dto;

import cn.com.git.cbs.exception.CbsRunTimeException;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * 不支持的消息类型异常
 * @author DengJia
 *
 */
public class UnsupportedMessageTypeException extends CbsRunTimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4780989815772064813L;
	private final static String ERRORCODE="PL1006";
	public UnsupportedMessageTypeException(String msgType) {
		super(ERRORCODE,ExceptionUtils.getErrorMessage(ERRORCODE, msgType));
	}
}
