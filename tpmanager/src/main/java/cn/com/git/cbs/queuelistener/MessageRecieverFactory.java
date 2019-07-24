package cn.com.git.cbs.queuelistener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.log.PlatformLogger;

/***
 * DefaultMessageListenerContainer的工厂，根据配置监听多个inQueue
 * @author DengJia
 *
 */
//@Profile({"asyncExecutor","asyncServer"})
//@Component
public class MessageRecieverFactory implements InitializingBean,
		ApplicationContextAware {
	
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	
	private ApplicationContext context;

//	@Value("#{'${GWQ.inQueueList}'.split(',')}")
	private String inQueueList;

//	@Value("pooledconnectionFactory")
	private String connectionFactoryName;

//	@Value("messageReciever")
	private String listenerBeanName;
//	@Value("${GWQ.concurrency}")
	private String concurrency;

	private String beanNamePrefix = "inQueueRecieverContainer";
	
	public MessageRecieverFactory(){
		LOGGER.info("11111111111111111111111111");
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {		
		
		// 将applicationContext转换为ConfigurableApplicationContext
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;

		// 获取bean工厂并转换为DefaultListableBeanFactory
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext
				.getBeanFactory();
		// 根据inQueueList注册MessageReciever对象
		String beanName = null;
		int i = 0;
		for (String inQueueName : inQueueList.split(",")) {
			beanName = StringUtils.join(beanNamePrefix, "-[",
					String.valueOf(i), "]");
			defaultListableBeanFactory.registerBeanDefinition(beanName,
					initListenerContainer(inQueueName).getBeanDefinition());
			i++;
		}
	}

	private BeanDefinitionBuilder initListenerContainer(String destQueueName) {
		BeanDefinitionBuilder listenerContainerBuilder = BeanDefinitionBuilder
				.genericBeanDefinition(DefaultMessageListenerContainer.class);
		listenerContainerBuilder.addPropertyReference("connectionFactory",
				connectionFactoryName);
		listenerContainerBuilder.addPropertyReference("messageListener",
				listenerBeanName);
		listenerContainerBuilder.addPropertyValue("destinationName",
				destQueueName);
		listenerContainerBuilder.addPropertyValue("concurrency", concurrency);
		
		listenerContainerBuilder.setInitMethodName("start");
		return listenerContainerBuilder;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}

	public String getInQueueList() {
		return inQueueList;
	}

	public void setInQueueList(String inQueueList) {
		this.inQueueList = inQueueList;
	}

	public String getConnectionFactoryName() {
		return connectionFactoryName;
	}

	public void setConnectionFactoryName(String connectionFactoryName) {
		this.connectionFactoryName = connectionFactoryName;
	}

	public String getListenerBeanName() {
		return listenerBeanName;
	}

	public void setListenerBeanName(String listenerBeanName) {
		this.listenerBeanName = listenerBeanName;
	}

	public String getConcurrency() {
		return concurrency;
	}

	public void setConcurrency(String concurrency) {
		this.concurrency = concurrency;
	}

	public String getBeanNamePrefix() {
		return beanNamePrefix;
	}

	public void setBeanNamePrefix(String beanNamePrefix) {
		this.beanNamePrefix = beanNamePrefix;
	}

}
