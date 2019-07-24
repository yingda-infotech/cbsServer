package cn.com.git.cbs.exception;

/**
 * 自定义运行时异常
 * @author DengJia
 *
 */
public class CbsRunTimeException extends RuntimeException {
	//异常码
	private String rspCode;
	private static final long serialVersionUID = 6859603146283282559L;

	/**
	 * 自定义运行时异常
	 * @param rspCode  异常码
	 * @param msg 异常信息
	 * @param t 异常
	 */
	public CbsRunTimeException(String rspCode, String msg, Throwable t) {
		super(msg, t);
		this.rspCode = rspCode;
	}

	/**
	 * 自定义运行时异常
	 * @param rspCode  异常码
	 * @param msg 异常信息
	 */
	public CbsRunTimeException(String rspCode, String msg) {
		this(rspCode, msg, null);
	}

	public String getRspCode() {
		return rspCode;
	}
}
