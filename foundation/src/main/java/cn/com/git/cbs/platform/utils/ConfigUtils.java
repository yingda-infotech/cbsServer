/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.platform.utils;

import java.util.Properties;

import cn.com.git.cbs.platform.utils.SpringContextUtils;

/**
 * Properties文件载入工具类. 可载入多个properties文件,
 * 相同的属性在最后载入的文件中的值将会覆盖之前的值，但以System的Property优先.
 * 暂时是Properties中获取，后期可能取数据库中的参数表，还有命令行等方式获取的参数
 * 
 * @author xiaxiaorui
 * @version 2016-11-28
 */

public class ConfigUtils {	

	/**
	 * 取出 exceptionCodeProperties  bean 的所有  String类型的Property
	 * 从/META-INF/errors/*.properties中加载
	 * @param errorCode 错误码
	 * @return 错误信息，如果错误码不存在则返回null
	 */
	public static String getErrorProperty(String errorCode) {
		String value = SpringContextUtils.getBean("exceptionCodeProperties",
				Properties.class).getProperty(errorCode);
		return value != null ? value : null;
	}

	/**
	 * 取出 production  bean 的所有  String类型的Property(application.properties)
	 * @param key 键值
	 * @return 键值对应的值，如果键值不存在则返回null
	 */
	public static String getAppProperty(String key) {
		String value = SpringContextUtils.getBean("appProps",
				Properties.class).getProperty(key);
		return value != null ? value : null;
	}
	
	/**
	 * 取出 para  bean 的所有  String类型的Property（未实现）
	 * @param key 键值
	 * @return 键值对应的值，如果键值不存在则返回null
	 */
	public static String getParaProperty(String key) {
		//暂时没有配置 参数
//		String value = SpringContextUtils.getBean("para",
//				Properties.class).getProperty(para);
//		return value != null ? value : null;
		return null;
	}
	
}
