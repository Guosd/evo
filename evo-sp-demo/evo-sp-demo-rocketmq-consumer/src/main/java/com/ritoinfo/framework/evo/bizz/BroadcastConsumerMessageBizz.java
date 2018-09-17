package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.common.Config;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-17 21:43
 */
@Service
public class BroadcastConsumerMessageBizz {
	public void receive() {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_broadcast_consume_group");
		consumer.setNamesrvAddr(Config.NAMESRV_ADDR);
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.setMessageModel(MessageModel.BROADCASTING);
		try {
			consumer.subscribe("TopicTest", "TagA || TagC || TagD");
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});

		try {
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		System.out.printf("Broadcast Consumer Started.%n");
	}
}
