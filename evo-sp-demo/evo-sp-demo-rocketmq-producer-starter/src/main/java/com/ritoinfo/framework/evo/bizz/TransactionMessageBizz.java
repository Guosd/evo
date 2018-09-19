package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.base.MessageBuilder;
import com.ritoinfo.framework.evo.common.CommonUtil;
import com.ritoinfo.framework.evo.producer.TransactionProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-17 16:26
 */
@Slf4j
@Service
public class TransactionMessageBizz {
	private static int count = 1;

	@Autowired
	private TransactionProducer producer;

	public SendResult send() {
		log.info("transaction send " + count);

		String key = CommonUtil.generateMessageKey();

		SendResult sendResult = producer.sendMessageInTransaction(MessageBuilder
				.of("Hello Transaction " + count)
				.topic("TopicTest")
				.tag("Tag_Transaction")
				.key(key).build(), key);

		count++;
		return sendResult;
	}
}
