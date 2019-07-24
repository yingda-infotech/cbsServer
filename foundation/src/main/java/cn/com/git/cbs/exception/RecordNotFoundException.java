package cn.com.git.cbs.exception;

import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * 记录未找到异常
 * @author DengJia
 *
 */
public class RecordNotFoundException extends CbsRunTimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9157880252599959733L;
	private final static PlatformLogger LOGGER=PlatformLogger.create();
	private final static String ERRCODE = "DB0002";

	public RecordNotFoundException(String tableName) {
		super(ERRCODE, ExceptionUtils.getErrorMessage(ERRCODE, tableName));
		LOGGER.error(this.getMessage());
	}

}
