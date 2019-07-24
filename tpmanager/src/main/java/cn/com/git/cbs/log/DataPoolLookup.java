/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.log;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

import cn.com.git.cbs.tpmanager.TranMainCtx;

/***
 * 用于检索数据缓冲池中数据的Log4j2 Plugin
 * 
 * @author DengJia
 * @see StrLookup
 */
@Plugin(name = "data", category = StrLookup.CATEGORY)
public class DataPoolLookup implements StrLookup {

	@Override
	public String lookup(String arg0) {
		String ret = null;
		if (TranMainCtx.getDataPool() != null && TranMainCtx.getDataPool().getValue(arg0) != null) {
			ret = TranMainCtx.getDataPool().getValue(arg0);
			if ("".equals(ret)) {
				ret = null;
			}
		}
		return ret;
	}

	@Override
	public String lookup(LogEvent arg0, String arg1) {
		return lookup(arg1);
	}

}
