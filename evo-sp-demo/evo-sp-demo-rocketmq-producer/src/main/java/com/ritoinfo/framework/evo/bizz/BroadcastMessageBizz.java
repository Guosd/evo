package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.mq.rocketmq.assist.MessageHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.producer.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-17 21:36
 */
@Slf4j
@Service
public class BroadcastMessageBizz {
	private static int count = 0;

	@Autowired
	private RocketMQProducer producer;

	public String send() {
		Message message = MessageHelper.createMessage("TopicTest", "TagA", MessageHelper.generateMessageKey(), "Hello RocketMQ Broadcast " + count++);
		log.info("broadcast send " + message);

		SendResult sendResult = producer.sendBroadcast(message);
		log.info("broadcast result " + sendResult);

		return null;
	}
}
