package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.annotation.MQTransactionProducer;
import com.maihaoche.starter.mq.base.AbstractMQTransactionProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * User: Kyll
 * Date: 2018-09-19 14:55
 */
@MQTransactionProducer(producerGroup = "test_async")
public class TransactionProducer extends AbstractMQTransactionProducer {
	@Override
	public LocalTransactionState executeLocalTransaction(Message message, Object o) {
		System.out.println(message);
		System.out.println(o);
		return LocalTransactionState.UNKNOW;
	}

	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
		System.out.println(messageExt);
		return LocalTransactionState.COMMIT_MESSAGE;
	}
}
