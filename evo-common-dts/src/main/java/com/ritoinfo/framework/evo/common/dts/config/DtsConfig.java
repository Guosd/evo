package com.ritoinfo.framework.evo.common.dts.config;

import com.ritoinfo.framework.evo.common.config.properties.ApplicationProperties;
import com.ritoinfo.framework.evo.common.dts.assist.DtsHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.assist.RocketMQHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.config.properties.RocketMQProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-10-25 15:21
 */
@Slf4j
@Configuration
public class DtsConfig {
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private RocketMQProperties rocketMQProperties;

	@Bean("logDefaultMQProducer")
	public DefaultMQProducer defaultMQProducer() {
		DefaultMQProducer producer = new DefaultMQProducer(DtsHelper.getLogProducerGroup(rocketMQProperties));
		producer.setNamesrvAddr(DtsHelper.getLogProducerNamesrvAddr(rocketMQProperties));
		producer.setInstanceName(RocketMQHelper.createInstanceName(applicationProperties.getApplicationName()));

		try {
			producer.start();
		} catch (MQClientException e) {
			throw new RocketMQOperateException("启动 DefaultMQProducer 失败", e);
		}

		return producer;
	}
}
