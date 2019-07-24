package cn.com.git.cbs.boot.conf;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import io.netty.channel.nio.NioEventLoopGroup;

@SpringBootConfiguration
public class BatchAgentConf {
	public static Charset ENCODING;

	@Bean("bossGroup")
	public NioEventLoopGroup getBossGroup(@Value("${batchAgent.bossGroupThreads}") int bossGroupThreads) {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(bossGroupThreads);
		return bossGroup;
	}

	@Bean("workerGroup")
	public NioEventLoopGroup getWorkerGroup(@Value("${batchAgent.workerGroupThreads}") int workerGroupThreads) {
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(workerGroupThreads);
		return workerGroup;
	}

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

	@Value("${batchAgent.msgCharset}")
	public void setEncoding(String charSet) {
		ENCODING = Charset.forName(charSet);
	}
}
