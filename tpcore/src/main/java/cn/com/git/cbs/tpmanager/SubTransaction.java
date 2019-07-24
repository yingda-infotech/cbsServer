/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.tpmanager;


/**
 * 子交易接口
 * @author DengJia
 *
 */
public interface SubTransaction {
	public void execute(DataPool datapool);
}
