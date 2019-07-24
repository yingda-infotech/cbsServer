package cn.com.git.cbs.batch.message;

import cn.com.git.cbs.model.DataObject;

public class BatchResponse extends DataObject {
	private String command;

	public BatchResponse() {
		super();
	}

	public BatchResponse(BatchRequest request) {
		super();
		this.command = request.getCommand();
		importObject(request);
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 获取返回码
	 * 
	 * @return 返回码
	 */
	public String getRspCode() {
		return getString("RspCode");
	}

	/**
	 * 设置返回码
	 * 
	 * @param rspCode
	 *            返回码
	 */
	public void setRspCode(String rspCode) {
		setString("RspCode", rspCode);
	}

	/**
	 * 获取返回信息
	 * 
	 * @return 返回信息
	 */
	public String getRspMsg() {
		return getString("RspMsg");
	}

	/**
	 * 设置返回信息
	 * 
	 * @param rspMsg
	 *            返回信息
	 */
	public void setRspMsg(String rspMsg) {
		setString("RspMsg", rspMsg);
	}
}
