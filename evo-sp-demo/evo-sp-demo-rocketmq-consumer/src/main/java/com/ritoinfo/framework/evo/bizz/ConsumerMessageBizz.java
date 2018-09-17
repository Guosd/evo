package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.common.Config;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-13 17:55
 */
@Service
public class ConsumerMessageBizz {
	public void receive() {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_consume_group");
		consumer.setNamesrvAddr(Config.NAMESRV_ADDR);

		try {
			consumer.subscribe("TopicTest", "*");
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});

		try {
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}

		System.out.printf("Consumer Started.%n");
	}
}
