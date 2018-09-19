package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.base.MessageBuilder;
import com.ritoinfo.framework.evo.common.CommonUtil;
import com.ritoinfo.framework.evo.producer.CommonMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-12 16:56
 */
@Slf4j
@Service
public class AsynchronouslyMessageBizz {
	private static int count = 1;

	@Autowired
	private CommonMQProducer producer;

	public SendResult send() {
		log.info("async send " + count);

		Message message = MessageBuilder
				.of("Hello Async " + count)
				.topic("TopicTest")
				.tag("Tag_Async")
				.key(CommonUtil.generateMessageKey()).build();

		final SendResult sendResult = null;

		producer.asyncSend(message, new SendCallback() {
			@Override
			public void onSuccess(SendResult sendResult) {
				sendResult = sendResult;
			}

			@Override
			public void onException(Throwable throwable) {
				log.error("", throwable);
			}
		});

		count++;
		return sendResult;
	}
}
