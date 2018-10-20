package com.ritoinfo.framework.evo.dts.common.assist;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.JsonUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.dts.common.annotation.RocketMQTransactionConsumer;
import com.ritoinfo.framework.evo.dts.common.annotation.RocketMQTransactionProducer;
import com.ritoinfo.framework.evo.dts.common.model.DtsLogMessage;
import com.ritoinfo.framework.evo.dts.common.model.DtsMessage;
import com.ritoinfo.framework.evo.mq.rocketmq.assist.RocketMQHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.config.properties.RocketMQProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-09-28 16:51
 */
public class DtsHelper {
	public static String getProducerNamesrvAddr(RocketMQProperties rocketMQProperties, RocketMQTransactionProducer producer) {
		String value = producer.namesrvAddr();
		return StringUtil.isBlank(value) ? RocketMQHelper.getProducerNamesrvAddr(rocketMQProperties) : value;
	}

	public static String getProducerGroup(RocketMQProperties rocketMQProperties, RocketMQTransactionProducer producer) {
		String value = producer.group();
		return StringUtil.isBlank(value) ? RocketMQHelper.getProducerGroup(rocketMQProperties) : value;
	}

	public static String getProducerTopic(RocketMQProperties rocketMQProperties, RocketMQTransactionProducer producer) {
		String value = producer.topic();
		return StringUtil.isBlank(value) ? RocketMQHelper.getProducerTopic(rocketMQProperties) : value;
	}

	public static String getProducerTags(RocketMQProperties rocketMQProperties, RocketMQTransactionProducer producer) {
		String value = producer.tags();
		return StringUtil.isBlank(value) ? RocketMQHelper.getProducerTags(rocketMQProperties) : value;
	}

	public static String getConsumerNamesrvAddr(RocketMQProperties rocketMQProperties, RocketMQTransactionConsumer consumer) {
		String value = consumer.namesrvAddr();
		return StringUtil.isBlank(value) ? RocketMQHelper.getConsumerNamesrvAddr(rocketMQProperties) : value;
	}

	public static String getConsumerGroup(RocketMQProperties rocketMQProperties, RocketMQTransactionConsumer consumer) {
		String value = consumer.group();
		return StringUtil.isBlank(value) ? RocketMQHelper.getConsumerGroup(rocketMQProperties) : value;
	}

	public static String getConsumerTopic(RocketMQProperties rocketMQProperties, RocketMQTransactionConsumer consumer) {
		String value = consumer.topic();
		return StringUtil.isBlank(value) ? RocketMQHelper.getConsumerTopic(rocketMQProperties) : value;
	}

	public static String getConsumerTags(RocketMQProperties rocketMQProperties, RocketMQTransactionConsumer consumer) {
		String value = consumer.tags();
		return StringUtil.isBlank(value) ? RocketMQHelper.getConsumerTags(rocketMQProperties) : value;
	}

	public static String getLogNamesrvAddr(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getLogNamesrvAddr();
		return StringUtil.isBlank(value) ? rocketMQProperties.getNamesrvAddr() : value;
	}

	public static String getLogGroup(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getLogGroup();
		return StringUtil.isBlank(value) ? rocketMQProperties.getGroup() : value;
	}

	public static String getLogTopic(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getLogTopic();
		return StringUtil.isBlank(value) ? rocketMQProperties.getTopic() : value;
	}

	public static String getLogTags(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getLogTags();
		return StringUtil.isBlank(value) ? rocketMQProperties.getTags() : value;
	}

	public static Message createDtsMessage(RocketMQProperties rocketMQProperties, RocketMQTransactionProducer producer, DtsMessage dtsMessage) {
		return RocketMQHelper.createMessage(getProducerTopic(rocketMQProperties, producer), getProducerTags(rocketMQProperties, producer), dtsMessage.getMessageKey(), dtsMessage);
	}

	public static Message createDtsLogMessage(RocketMQProperties rocketMQProperties, String keys, DtsMessage dtsMessage, String role, String step) {
		DtsLogMessage dtsLogMessage = new DtsLogMessage();
		BeanUtil.copy(dtsLogMessage, dtsMessage);

		dtsLogMessage.setRole(role);
		dtsLogMessage.setStep(step);

		return RocketMQHelper.createMessage(getLogTopic(rocketMQProperties), getLogTags(rocketMQProperties), "log_" + keys, dtsLogMessage);
	}

	public static List<DtsMessage> parseDtsMessage(List<MessageExt> messageExtList) {
		return parse(messageExtList, DtsMessage.class);
	}

	public static List<DtsLogMessage> parseDtsLogMessage(List<MessageExt> messageExtList) {
		return parse(messageExtList, DtsLogMessage.class);
	}

	public static DtsMessage parseDtsMessage(MessageExt messageExt) {
		return parse(messageExt, DtsMessage.class);
	}

	public static DtsLogMessage parseDtsLogMessage(MessageExt messageExt) {
		return parse(messageExt, DtsLogMessage.class);
	}

	public static <T> List<T> parse(List<MessageExt> messageExtList, Class<T> clazz) {
		List<T> list = new ArrayList<>();
		for (MessageExt messageExt : messageExtList) {
			list.add(parse(messageExt, clazz));
		}
		return list;
	}

	public static <T> T parse(MessageExt messageExt, Class<T> clazz) {
		String content;
		try {
			content = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RocketMQOperateException("解析 MessageExt 失败", e);
		}

		return JsonUtil.jsonToObject(content, clazz);
	}
}
