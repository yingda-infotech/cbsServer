/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.message;

import cn.com.git.cbs.model.DataObject;

/**
 * 消息头
 * @author DengJia
 *
 */
public class MessageHeader extends DataObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5656379412919488762L;

	public MessageHeader() {
		super();
	}

	public MessageHeader(String msgType, String structType, String tranCode,
			String fileFlag, String macValue, String srvName) {
		super();
		setString("macvalue", macValue);  //################################
		setString("trancode", tranCode); 
		setString("srvname", srvName);   //CBS     ,补齐8位的系统码
		setString("msgtype", msgType);   //RQ或者RS
		setString("fileflag", fileFlag);  //0或者1，是否有文件
		setString("structtype", structType);  //XML
	}

	public String getMacValue() {
		return getString("macvalue");
	}

	public void setMacValue(String macValue) {
		setString("macvalue", macValue);
	}

	public String getTranCode() {
		return getString("trancode");
	}

	public void setTranCode(String tranCode) {
		setString("trancode", tranCode);
	}

	public String getSrvName() {
		return getString("srvname");
	}

	public void setSrvName(String srvName) {
		setString("srvname", srvName);
	}

	public String getMsgType() {
		return getString("msgtype");
	}

	public void setMsgType(String msgType) {
		setString("msgtype", msgType);
	}

	public String getFileFlag() {
		return getString("fileflag");
	}

	public void setFileFlag(String fileFlag) {
		setString("fileflag", fileFlag);
	}

	public String getStructType() {
		return getString("structtype");
	}

	public void setStructType(String structType) {
		setString("structtype", structType);
	}
}
