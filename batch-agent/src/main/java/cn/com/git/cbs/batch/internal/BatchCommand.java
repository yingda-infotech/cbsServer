package cn.com.git.cbs.batch.internal;

/**
 * 批处理的报文结构
 * @author DengJia
 *
 */
public class BatchCommand{
	
	//报文头	 
	private String command;
	
	//报文体
	private String commandBody;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommandBody() {
		return commandBody;
	}

	public void setCommandBody(String commandBody) {
		this.commandBody = commandBody;
	}
}
