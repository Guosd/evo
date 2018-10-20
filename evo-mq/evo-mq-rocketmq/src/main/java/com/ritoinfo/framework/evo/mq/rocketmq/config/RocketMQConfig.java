package com.ritoinfo.framework.evo.mq.rocketmq.config;

import com.ritoinfo.framework.evo.common.config.properties.ApplicationProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import com.ritoinfo.framework.evo.mq.rocketmq.assist.RocketMQHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.config.properties.RocketMQProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-09-28 09:56
 */
@Slf4j
@Configuration
public class RocketMQConfig {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private RocketMQProperties rocketMQProperties;

	@Bean("defaultMQProducer")
	public DefaultMQProducer defaultMQProducer() {
		DefaultMQProducer producer = new DefaultMQProducer(RocketMQHelper.getProducerGroup(rocketMQProperties));
		producer.setNamesrvAddr(RocketMQHelper.getProducerNamesrvAddr(rocketMQProperties));
		producer.setInstanceName(RocketMQHelper.createInstanceName(applicationProperties.getApplicationName()));

		try {
			producer.start();
		} catch (MQClientException e) {
			throw new RocketMQOperateException("启动 DefaultMQProducer 失败", e);
		}

		return producer;
	}

	@PostConstruct
	public void defaultMQPushConsumer() {
		Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RocketMQConsumer.class);
		beanMap.values().forEach(o -> {
			RocketMQConsumer rocketMQConsumer = o.getClass().getAnnotation(RocketMQConsumer.class);
			DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQHelper.getConsumerGroup(rocketMQProperties, rocketMQConsumer));
			consumer.setNamesrvAddr(RocketMQHelper.getConsumerNamesrvAddr(rocketMQProperties, rocketMQConsumer));
			consumer.setConsumeFromWhere(rocketMQConsumer.consumeFromWhere());
			consumer.setMessageModel(rocketMQConsumer.messageModel());

			try {
				consumer.subscribe(RocketMQHelper.getConsumerTopic(rocketMQProperties, rocketMQConsumer), RocketMQHelper.getConsumerTags(rocketMQProperties, rocketMQConsumer));
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
