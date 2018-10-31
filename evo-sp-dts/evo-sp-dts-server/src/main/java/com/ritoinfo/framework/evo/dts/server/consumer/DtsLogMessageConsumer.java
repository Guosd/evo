package com.ritoinfo.framework.evo.dts.server.consumer;

import com.ritoinfo.framework.evo.dts.common.assist.DtsHelper;
import com.ritoinfo.framework.evo.dts.common.model.DtsLogMessageDto;
import com.ritoinfo.framework.evo.dts.server.event.DtsLogMessageEvent;
import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-10-20 20:09
 */
@RocketMQConsumer
@Slf4j
@Component
public class DtsLogMessageConsumer implements MessageListenerConcurrently {
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		log.info("接收消息 List<MessageExt> = {}，ConsumeConcurrentlyContext = {}", list, consumeConcurrentlyContext);

		for (DtsLogMessageDto dtsLogMessageDto : DtsHelper.parse(list, DtsLogMessageDto.class)) {
			applicationContext.publishEvent(new DtsLogMessageEvent(this, dtsLogMessageDto));
		}

		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
