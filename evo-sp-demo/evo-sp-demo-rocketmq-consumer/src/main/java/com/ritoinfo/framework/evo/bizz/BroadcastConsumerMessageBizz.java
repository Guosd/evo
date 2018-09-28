package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-09-17 21:43
 */
@RocketMQConsumer(consumerGroup = "test_broadcast_group", topic = "TopicTest", tags = "TagA || TagC || TagD", consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, messageModel = MessageModel.BROADCASTING)
@Slf4j
@Service
public class BroadcastConsumerMessageBizz implements MessageListenerConcurrently {
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		log.info("Receive " + list + ", " + consumeConcurrentlyContext);
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
