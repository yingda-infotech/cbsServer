package cn.com.git.cbs.batch.message;

import cn.com.git.cbs.model.DataObject;

public class BatchRequest extends DataObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5451890887233726514L;
	private String command;

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getProgName() {
		return getString("ProgName");
	}

	public void setProgName(String progName) {
		setString("ProgName", progName);
	}

	public String getPara() {
		return getString("Para");
	}

	public void setPara(String para) {
		setString("Para", para);
	}
}
