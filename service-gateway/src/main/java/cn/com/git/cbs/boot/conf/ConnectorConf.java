package cn.com.git.cbs.boot.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import io.netty.channel.nio.NioEventLoopGroup;

@SpringBootConfiguration
public class ConnectorConf {
	@Bean("bizExecutor")
	public ListeningExecutorService getBizExecutor(@Value("${bizExecutor.queueCapacity}") int queueCapacity,
			@Value("${bizExecutor.corePoolSize}") int corePoolSize,
			@Value("${bizExecutor.maxPoolSize}") int maxPoolSize,
			@Value("${bizExecutor.threadNamePrefix}") String threadNamePrefix) {
		ThreadPoolExecutorFactoryBean factory = new ThreadPoolExecutorFactoryBean();
		factory.setCorePoolSize(corePoolSize);
		factory.setMaxPoolSize(maxPoolSize);
		factory.setThreadNamePrefix(threadNamePrefix);
		factory.setQueueCapacity(queueCapacity);
		factory.initialize();
		return MoreExecutors.listeningDecorator(factory.getObject());
	}

	@Bean("bossGroup")
	public NioEventLoopGroup getBossGroup(@Value("${netty.bossGroupThreads}") int bossGroupThreads) {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(bossGroupThreads);
		return bossGroup;
	}

	@Bean("workerGroup")
	public NioEventLoopGroup getWorkerGroup(@Value("${netty.workerGroupThreads}") int workerGroupThreads) {
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(workerGroupThreads);
		return workerGroup;
	}
}
