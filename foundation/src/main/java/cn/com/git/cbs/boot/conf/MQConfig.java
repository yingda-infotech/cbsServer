package cn.com.git.cbs.boot.conf;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootConfiguration
@ConditionalOnProperty(name = "useMQ", havingValue = "true")
public class MQConfig {
	@Bean("pooledconnectionFactory")
	public PooledConnectionFactory getPooledConnectionFactory(@Value("${jms.brokerURL}") String brokerURL,
			@Value("${jms.userName}") String userName, @Value("${jms.password}") String password) {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(brokerURL);
		factory.setUseAsyncSend(true);
		factory.setUserName(userName);
		factory.setPassword(password);
		PooledConnectionFactory pooledFactory = new PooledConnectionFactory(factory);
		return pooledFactory;
	}
	
	@Bean("queueJmsTemplate")
	public JmsTemplate getJmsTemplate(@Autowired PooledConnectionFactory factory,
			@Value("${jms.timeToLive}") long timeToLive) {
		JmsTemplate ret = new JmsTemplate();
		ret.setConnectionFactory(factory);
		ret.setTimeToLive(timeToLive);
		return ret;
	}
}
