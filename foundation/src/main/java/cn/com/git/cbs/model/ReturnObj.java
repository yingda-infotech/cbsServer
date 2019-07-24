/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.model;

/**
 * 仿C写法，需要把sql执行的结果和成功与否标志一起返回
 * 数据库操作的返回结果集
 * @author xia
 *
 */
public class ReturnObj <T>{
	
	//SQLFAILURE失败，1是成功
	/**
	 * 操作成功
	 */
	public final static int SUCCESSFUL = 1;
	
	/**
	 * sql查询，查询无数据
	 */
	public final static int NOTFOUND = 0;
	
	/**
	 * 操作失败
	 */
	public final static int FAILURE = -9999;
	
	//sql执行结果
	private int retValue;	
	
	//方法返回结果，可能是一个对象，也可能是一个list.也有可能为空
	private T data;

	/**
	 * @return 返回操作结果
	 */
	public int getRetValue() {
		return retValue;
	}

	/**
	 * @param retValue 设置返回结果
	 */
	public void setRetValue(int retValue) {
		this.retValue = retValue;
	}

	/**
	 * 
	 * @return 返回操作的数据
	 */
	public T getData() {
		return data;
	}

	/**
	 * 
	 * @param data 设置操作的数据
	 */
	public void setData(T data) {
		this.data = data;
	}

}
