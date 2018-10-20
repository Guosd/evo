package com.ritoinfo.framework.evo.mq.rocketmq.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-10-11 14:45
 */
@Data
@Configuration
public class RocketMQProperties {
	@Value("${rocketmq.namesrv-addr:}")
	private String namesrvAddr;
	@Value("${rocketmq.group:}")
	private String group;
	@Value("${rocketmq.topic:}")
	private String topic;
	@Value("${rocketmq.tags:}")
	private String tags;

	@Value("${rocketmq.producer.namesrv-addr:}")
	private String producerNamesrvAddr;
	@Value("${rocketmq.producer.group:}")
	private String producerGroup;
	@Value("${rocketmq.producer.topic:}")
	private String producerTopic;
	@Value("${rocketmq.producer.tags:}")
	private String producerTags;

	@Value("${rocketmq.producer.transaction.executor-service.core-pool-size:2}")
	private Integer corePoolSize;
	@Value("${rocketmq.producer.transaction.executor-service.maximum-pool-size:5}")
	private Integer maximumPoolSize;
	@Value("${rocketmq.producer.transaction.executor-service.keep-alive-time:100}")
	private Long keepAliveTime;
	@Value("${rocketmq.producer.transaction.executor-service.capacity:200}")
	private Integer capacity;
	@Value("${rocketmq.producer.transaction.executor-service.thread-name:rocketmq-transaction-executor}")
	private String threadName;

	@Value("${rocketmq.consumer.namesrv-addr:}")
	private String consumerNamesrvAddr;
	@Value("${rocketmq.consumer.group:}")
	private String consumerGroup;
	@Value("${rocketmq.consumer.topic:}")
	private String consumerTopic;
	@Value("${rocketmq.consumer.tags:}")
	private String consumerTags;

	@Value("${rocketmq.dts.log.namesrv-addr:}")
	private String logNamesrvAddr;
	@Value("${rocketmq.dts.log.group:evo_dts_group}")
	private String logGroup;
	@Value("${rocketmq.dts.log.topic:evo_dts_topic}")
	private String logTopic;
	@Value("${rocketmq.dts.log.tags:dts_log_tag}")
	private String logTags;
}
