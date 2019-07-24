package cn.com.git.cbs.boot.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import cn.com.git.cbs.common.utils.ValidateUtil;

@SpringBootConfiguration
public class UtilsBeans {
	@Autowired
	private ResourceLoader rl;
	/***
	 * 校验用bean，被ValidateUtil引用。
	 * @see ValidateUtil
	 * 
	 * @return LocalValiatorFactoryBean实例
	 */
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		return new LocalValidatorFactoryBean();
	}	
	
	@Bean("exceptionCodeProperties")
	public PropertiesFactoryBean exceptionCodeProperties() throws Exception {
		PropertiesFactoryBean exceptionCodeProperties=new PropertiesFactoryBean();
		exceptionCodeProperties.setSingleton(true);
		exceptionCodeProperties.setIgnoreResourceNotFound(true);
		exceptionCodeProperties.setFileEncoding("UTF-8");
		exceptionCodeProperties.setLocations(ResourcePatternUtils.getResourcePatternResolver(rl).getResources("classpath*:/META-INF/errors/*.properties"));
		return exceptionCodeProperties;
	}
}
