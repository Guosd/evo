package com.ritoinfo.framework.evo.mq.rocketmq.assist;

import com.ritoinfo.framework.evo.common.uitl.AlgorithmUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.JsonUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import com.ritoinfo.framework.evo.mq.rocketmq.config.properties.RocketMQProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

/**
 * User: Kyll
 * Date: 2018-10-19 13:36
 */
public class RocketMQHelper {
	public static String generateMessageKey(String applicationName) {
		return applicationName + "_" + DateUtil.formatDatetimeCompact(DateUtil.now()) + "_" + AlgorithmUtil.randomNumber(8) + "_" + AlgorithmUtil.uuid();
	}

	public static String createInstanceName(String applicationName) {
		return applicationName + "_" + AlgorithmUtil.randomNumber(8) + "_" + AlgorithmUtil.uuid();
	}

	public static String getProducerNamesrvAddr(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getProducerNamesrvAddr();
		return StringUtil.isBlank(value) ? rocketMQProperties.getNamesrvAddr() : value;
	}

	public static String getProducerGroup(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getProducerGroup();
		return StringUtil.isBlank(value) ? rocketMQProperties.getGroup() : value;
	}

	public static String getProducerTopic(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getProducerTopic();
		return StringUtil.isBlank(value) ? rocketMQProperties.getTopic() : value;
	}

	public static String getProducerTags(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getProducerTags();
		return StringUtil.isBlank(value) ? rocketMQProperties.getTags() : value;
	}

	public static String getConsumerNamesrvAddr(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getConsumerNamesrvAddr();
		return StringUtil.isBlank(value) ? rocketMQProperties.getNamesrvAddr() : value;
	}

	public static String getConsumerGroup(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getConsumerGroup();
		return StringUtil.isBlank(value) ? rocketMQProperties.getGroup() : value;
	}

	public static String getConsumerTopic(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getConsumerTopic();
		return StringUtil.isBlank(value) ? rocketMQProperties.getTopic() : value;
	}

	public static String getConsumerTags(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getConsumerTags();
		return StringUtil.isBlank(value) ? rocketMQProperties.getTags() : value;
	}

	public static String getConsumerNamesrvAddr(RocketMQProperties rocketMQProperties, RocketMQConsumer consumer) {
		String value = consumer.namesrvAddr();
		return StringUtil.isBlank(value) ? getConsumerNamesrvAddr(rocketMQProperties) : value;
	}

	public static String getConsumerGroup(RocketMQProperties rocketMQProperties, RocketMQConsumer consumer) {
		String value = consumer.group();
		return StringUtil.isBlank(value) ? getConsumerGroup(rocketMQProperties) : value;
	}

	public static String getConsumerTopic(RocketMQProperties rocketMQProperties, RocketMQConsumer consumer) {
		String value = consumer.topic();
		return StringUtil.isBlank(value) ? getConsumerTopic(rocketMQProperties) : value;
	}

	public static String getConsumerTags(RocketMQProperties rocketMQProperties, RocketMQConsumer consumer) {
		String value = consumer.tags();
		return StringUtil.isBlank(value) ? getConsumerTags(rocketMQProperties) : value;
	}

	public static Message createMessage(String topic, String tags, String keys, Object content) {
		try {
			return new Message(topic, tags, keys, JsonUtil.objectToJson(content).getBytes(RemotingHelper.DEFAULT_CHARSET));
		} catch (UnsupportedEncodingException e) {
			throw new RocketMQOperateException("建立 Message 失败", e);
		}
	}
}
