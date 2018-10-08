package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQTransactionProducer;
import com.ritoinfo.framework.evo.mq.rocketmq.assist.MessageHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.listener.AbstractRocketMQTransactionProcesser;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Kyll
 * Date: 2018-09-17 16:26
 */
@RocketMQTransactionProducer(producerGroup = "test_transaction_group_1")
@Slf4j
@Service
public class TransactionMessageBizz extends AbstractRocketMQTransactionProcesser {
	private static int count = 0;

	private AtomicInteger transactionIndex = new AtomicInteger(0);
	private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

	@Override
	public LocalTransactionState executeLocalTransaction(Message message, Object o) {
		log.info("executeLocalTransaction " + message + ", " + o);

		int value = transactionIndex.getAndIncrement();
		int status = value % 3;
		localTrans.put(message.getTransactionId(), status);

		return LocalTransactionState.UNKNOW;
	}

	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
		log.info("checkLocalTransaction " + messageExt);


		return LocalTransactionState.COMMIT_MESSAGE;
	}

	public String send() {
		String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
		Message message = MessageHelper.createMessage("TopicTest", tags[new Random().nextInt(tags.length)], MessageHelper.generateMessageKey(), "Hello RocketMQ Transaction " + count++);
		log.info("transaction send " + message);

		SendResult sendResult = sendMessageInTransaction(message, null);
		log.info("transaction result " + sendResult);

		return null;
	}
}
