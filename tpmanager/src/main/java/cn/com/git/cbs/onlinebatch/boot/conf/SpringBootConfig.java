package cn.com.git.cbs.onlinebatch.boot.conf;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

import cn.com.git.cbs.onlinebatch.BatchCommandReciver;

@SpringBootConfiguration
@ConditionalOnProperty(name = "useMQ", havingValue = "true")
public class SpringBootConfig {
	@Autowired
	private BatchCommandReciver batchCommandReciver;

	@Value("${jms.concurrentConsumers}")
	private int concurrentConsumers;

	@Autowired
	private ConnectionFactory connectionFactory;

	@Value("${jms.onlinebatchqueue.name}")
	private String destinationName;

	@Bean("listenerContainer")
	public MessageListenerContainer getListenerContainer() {
		SimpleMessageListenerContainer ret = new SimpleMessageListenerContainer();
		ret.setConcurrentConsumers(concurrentConsumers);
		ret.setConnectionFactory(connectionFactory);
		ret.setDestinationName(destinationName);
		ret.setMessageListener(batchCommandReciver);
		return ret;
	}
}
