/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.platform.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.log.PlatformLogger;

/**
 * 功能描述：获取spring容器，以访问容器中定义的其他bean
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	private final static PlatformLogger LOGGER=PlatformLogger.create();
	/**
	 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
	 * 
	 * 
	 */
	private static ApplicationContext applicationContext; // Spring应用上下文环境

	/**
	 *
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * 
	 * @param applicationContext Spring的应用上下文对象
	 * @exception BeansException 不可能抛出的异常
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext 获取Spring的ApplicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 *            Bean的名字
	 * @return 一个以所给名字注册的bean的实例
	 * @exception BeansException 获取Bean失败时的异常
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * 获取对象
	 * 
	 * @param cls
	 *            Bean的Class类型
	 * @param <T> 指定的Class类型
	 * @return 一个以所给Class类型注册的bean的实例
	 * @exception BeansException 获取Bean失败时的异常
	 */
	public static <T> T getBean(Class<T> cls) throws BeansException {
		return applicationContext.getBean(cls);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * 
	 * @param name
	 *            bean注册名
	 * @param requiredType
	 *            返回对象类型
	 * @param <T> 指定的Class类型
	 * @return Object 返回requiredType类型对象
	 * @exception BeansException 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 */
	public static <T> T getBean(String name, Class<T> requiredType)
			throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * 
	 * @param name Bean名称
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		if (StringUtils.isEmpty(name)) {
			LOGGER.warn("传入的Bean名称不能为空");
			return false;
		}			
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * 
	 * @param name Bean名称
	 * @return boolean
	 * @exception NoSuchBeanDefinitionException Bean未找到的异常
	 */
	public static boolean isSingleton(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * 获取给定名称Bean的类型.
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * @param name Bean名称
	 * @return Class 注册对象的类型
	 * @exception NoSuchBeanDefinitionException Bean未找到的异常
	 */
	public static Class<?> getType(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	} 
	
	/**
	 * 获取指定路径的Resource对象.
	 * 路径包括：
	 * <ol>
	 * <li>支持完整路径："file:C:/test.dat". </li>
	 * <li>支持classpath URL: "classpath:test.dat". </li>
	 * <li>支持相对路径："WEB-INF/test.dat". </li>
	 * </ol>
	 * @param path Resource路径
	 * @return Resource对象
	 * @see ApplicationContext#getResource(String)
	 */
	public static Resource getResource(String path) {
		return applicationContext.getResource(path);
	}
	
	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * 
	 * @param name Bean名称
	 * @return 别名数组
	 * @exception NoSuchBeanDefinitionException Bean未找到的异常
	 */
	public static String[] getAliases(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}
}
