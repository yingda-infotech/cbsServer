/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.batch.log;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

import cn.com.git.cbs.batch.context.BatchDataHolder;

/***
 * 用于检索数据缓冲池中数据的Log4j2 Plugin
 * 
 * @author DengJia
 * @see StrLookup
 */
@Plugin(name = "batch", category = StrLookup.CATEGORY)
public class BatchContextLookup implements StrLookup {

	@Override
	public String lookup(String arg0) {
		String ret = null;
		if (BatchDataHolder.getData() != null && BatchDataHolder.getData().getString(arg0) != null) {
			ret = BatchDataHolder.getData().getString(arg0);
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
