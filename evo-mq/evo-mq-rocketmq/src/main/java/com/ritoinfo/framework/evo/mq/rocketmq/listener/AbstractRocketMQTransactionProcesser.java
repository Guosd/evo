package com.ritoinfo.framework.evo.mq.rocketmq.listener;

import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * User: Kyll
 * Date: 2018-09-28 13:46
 */
@Slf4j
public abstract class AbstractRocketMQTransactionProcesser implements TransactionListener {
	@Setter
	private TransactionMQProducer transactionMQProducer;

	public TransactionSendResult sendMessageInTransaction(Message message, Object arg) {
		try {
			return transactionMQProducer.sendMessageInTransaction(message, arg);
		} catch (MQClientException e) {
			throw new RocketMQOperateException("发送 transaction 消息失败", e);
		}
	}
}
