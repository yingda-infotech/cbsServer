package cn.com.git.cbs.launcher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import cn.com.git.cbs.connector.NettyDirectConnector;

/***
 * 联机交易平台启动类
 * 
 * @author DengJia
 *
 */
@SpringBootApplication
@ComponentScan("cn.com.git.cbs")
public class CBSMain {
	static {
//		System.setProperty("org.jboss.logging.provider", "slf4j");
//		SLF4JBridgeHandler.removeHandlersForRootLogger();
//		SLF4JBridgeHandler.install();
//
//		System.setProperty("log4j.configurationFile", "./conf/log4j2.xml");
	}
	/***
	 * main方法
	 * 
	 * @param aArgs
	 *            传入参数
	 */
	public static void main(String[] aArgs) {
		SpringApplication springApplication = new SpringApplication(CBSMain.class);
//		System.setProperty("log4j.configurationFile", "./conf/log4j2.xml");
		ApplicationContext applicationContext = springApplication.run(aArgs);
		applicationContext.getBean(NettyDirectConnector.class).start();
//		applicationContext.registerShutdownHook();
//		applicationContext.close();
	}
}
