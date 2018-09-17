package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.common.Config;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-17 22:33
 */
@Service
public class ScheduleConsumerMessageBizz {
	public void receive() {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_schedule_consume_group");
		consumer.setNamesrvAddr(Config.NAMESRV_ADDR);

		try {
			consumer.subscribe("TopicTest", "*");
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
			for (MessageExt message : messages) {
				System.out.println("Receive message[msgId=" + message.getMsgId() + "] "
						+ (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
			}
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});

		try {
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}
}
