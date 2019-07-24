package cn.com.git.cbs.batch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import cn.com.git.cbs.batch.agent.BatchAgent;
import cn.com.git.cbs.log.PlatformLogger;

@SpringBootApplication
@ComponentScan("cn.com.git.cbs")
@MapperScan("cn.com.git.cbs")
public class BatchAgentApplication {
	private final static PlatformLogger LOGGER = PlatformLogger.create();

	public static void main(String[] args) {
		
		SpringApplication springApplication = new SpringApplication(BatchAgentApplication.class);
//		System.setProperty("log4j.configurationFile", "./conf/log4j2.xml");
		ApplicationContext applicationContext = springApplication.run(args);
		LOGGER.info("==========CBS Batch Agent Started===================");
		applicationContext.getBean(BatchAgent.class).start();
	}
}
