package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-09-13 17:55
 */
@RocketMQConsumer(consumerGroup = "test_group", topic = "TopicTest", tags = "*")
@Slf4j
@Service
public class ConsumerMessageBizz implements MessageListenerConcurrently {
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		log.info("Receive " + list + ", " + consumeConcurrentlyContext);
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
