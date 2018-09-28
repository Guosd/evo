package com.ritoinfo.framework.evo.mq.rocketmq.config;

import com.ritoinfo.framework.evo.common.uitl.ConcurrentUtil;
import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQTransactionProducer;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import com.ritoinfo.framework.evo.mq.rocketmq.listener.AbstractRocketMQTransactionProcesser;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * User: Kyll
 * Date: 2018-09-28 09:56
 */
@Slf4j
@Configuration
public class RocketMQConfig {
	@Value("${rocketmq.producer-group}")
	private String producerGroup;
	@Value("${rocketmq.namesrv-addr}")
	private String namesrvAddr;
	@Value("${rocketmq.transaction.executor-service.core-pool-size:2}")
	private Integer corePoolSize;
	@Value("${rocketmq.transaction.executor-service.maximum-pool-size:5}")
	private Integer maximumPoolSize;
	@Value("${rocketmq.transaction.executor-service.keep-alive-time:100}")
	private Long keepAliveTime;
	@Value("${rocketmq.transaction.executor-service.capacity:200}")
	private Integer capacity;
	@Value("${rocketmq.transaction.executor-service.thread-name:client-transaction-msg-check-thread}")
	private String threadName;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean("defaultMQProducer")
	public DefaultMQProducer defaultMQProducer() {
		DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
		producer.setNamesrvAddr(namesrvAddr);

		try {
			producer.start();
		} catch (MQClientException e) {
			throw new RocketMQOperateException("启动 DefaultMQProducer 失败", e);
		}

		return producer;
	}

	@PostConstruct
	public void transactionMQProducer() {
		ExecutorService executorService = ConcurrentUtil.createExecutorService(corePoolSize, maximumPoolSize, keepAliveTime, capacity, threadName);

		Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RocketMQTransactionProducer.class);
		beanMap.values().forEach(o -> {
			AbstractRocketMQTransactionProcesser transactionListener = (AbstractRocketMQTransactionProcesser) o;

			TransactionMQProducer producer = new TransactionMQProducer(o.getClass().getAnnotation(RocketMQTransactionProducer.class).producerGroup());
			producer.setNamesrvAddr(namesrvAddr);
			producer.setExecutorService(executorService);
			producer.setTransactionListener(transactionListener);

			transactionListener.setTransactionMQProducer(producer);

			try {
				producer.start();
			} catch (MQClientException e) {
				throw new RocketMQOperateException("启动 TransactionMQProducer 失败", e);
			}
		});
	}

	@PostConstruct
	public void defaultMQPushConsumer() {
		Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RocketMQConsumer.class);
		beanMap.values().forEach(o -> {
			RocketMQConsumer rocketMQConsumer = o.getClass().getAnnotation(RocketMQConsumer.class);
			DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMQConsumer.consumerGroup());
			consumer.setNamesrvAddr(namesrvAddr);
			consumer.setMessageModel(rocketMQConsumer.messageModel());

			try {
				consumer.subscribe(rocketMQConsumer.topic(), rocketMQConsumer.tags());
			} catch (MQClientException e) {
				throw new RocketMQOperateException("订阅消息失败", e);
			}

			if (o instanceof MessageListenerConcurrently) {
				consumer.registerMessageListener((MessageListenerConcurrently) o);
			} else if (o instanceof MessageListenerOrderly) {
				consumer.registerMessageListener((MessageListenerOrderly) o);
			} else {
				throw new RocketMQOperateException("不支持的 MessageListener " + o);
			}

			try {
				consumer.start();
			} catch (MQClientException e) {
				throw new RocketMQOperateException("启动 DefaultMQPushConsumer 失败 " + o.getClass(), e);
			}
		});
	}
}
