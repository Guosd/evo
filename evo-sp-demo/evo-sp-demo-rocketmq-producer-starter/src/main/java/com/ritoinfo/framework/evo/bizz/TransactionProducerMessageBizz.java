package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.base.MessageBuilder;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-17 16:26
 */
@Service
public class TransactionProducerMessageBizz {
	@Autowired
	private TransactionProducer producer;

	public void send() {
		for (int i = 0; i < 1; i++) {
			String key = "KEY " + i;
			Message message = MessageBuilder.of("Hello Transaction " + i).topic("TopicTest").tag("TagA").key(key).build();
			SendResult sendResult = producer.sendMessageInTransaction(message, key);
			System.out.println(sendResult);
		}
	}
}
