package com.ritoinfo.framework.evo.sp.demo.consumer;

import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-10-20 20:09
 */
@RocketMQConsumer
@Slf4j
@Component
public class DtsMessageConsumer implements MessageListenerConcurrently {
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		log.info("接收消息 List<MessageExt> = {}，ConsumeConcurrentlyContext = {}", list, consumeConcurrentlyContext);



		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
