package com.ritoinfo.framework.evo.common.dts.assist;

import com.ritoinfo.framework.evo.common.dts.annotation.RocketMQTransactionConsumer;
import com.ritoinfo.framework.evo.common.dts.annotation.RocketMQTransactionProducer;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.JsonUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.mq.rocketmq.assist.RocketMQHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.config.properties.RocketMQProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import com.ritoinfo.framework.evo.sp.dts.dto.DtsBizzMessageDto;
import com.ritoinfo.framework.evo.sp.dts.dto.DtsLogMessageDto;
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

	public static String getLogProducerNamesrvAddr(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getLogProducerNamesrvAddr();
		return StringUtil.isBlank(value) ? rocketMQProperties.getNamesrvAddr() : value;
	}

	public static String getLogProducerGroup(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getLogProducerGroup();
		return StringUtil.isBlank(value) ? rocketMQProperties.getGroup() : value;
	}

	public static String getLogProducerTopic(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getLogProducerTopic();
		return StringUtil.isBlank(value) ? rocketMQProperties.getTopic() : value;
	}

	public static String getLogProducerTags(RocketMQProperties rocketMQProperties) {
		String value = rocketMQProperties.getLogProducerTags();
		return StringUtil.isBlank(value) ? rocketMQProperties.getTags() : value;
	}

	public static Message createDtsMessage(RocketMQProperties rocketMQProperties, RocketMQTransactionProducer producer, DtsBizzMessageDto dtsBizzMessageDto) {
		return RocketMQHelper.createMessage(getProducerTopic(rocketMQProperties, producer), getProducerTags(rocketMQProperties, producer), dtsBizzMessageDto.getMessageKey(), dtsBizzMessageDto);
	}

	public static Message createDtsLogMessage(RocketMQProperties rocketMQProperties, String keys, DtsBizzMessageDto dtsBizzMessageDto, String role, String step) {
		DtsLogMessageDto dtsLogMessageDto = new DtsLogMessageDto();
		BeanUtil.copy(dtsLogMessageDto, dtsBizzMessageDto);

		dtsLogMessageDto.setLogMessageKey("log_" + keys);
		dtsLogMessageDto.setRole(role);
		dtsLogMessageDto.setStep(step);

		return RocketMQHelper.createMessage(getLogProducerTopic(rocketMQProperties), getLogProducerTags(rocketMQProperties), dtsLogMessageDto.getLogMessageKey(), dtsLogMessageDto);
	}

	public static List<DtsBizzMessageDto> parseDtsMessage(List<MessageExt> messageExtList) {
		return parse(messageExtList, DtsBizzMessageDto.class);
	}

	public static List<DtsLogMessageDto> parseDtsLogMessage(List<MessageExt> messageExtList) {
		return parse(messageExtList, DtsLogMessageDto.class);
	}

	public static DtsBizzMessageDto parseDtsMessage(MessageExt messageExt) {
		return parse(messageExt, DtsBizzMessageDto.class);
	}

	public static DtsLogMessageDto parseDtsLogMessage(MessageExt messageExt) {
		return parse(messageExt, DtsLogMessageDto.class);
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
