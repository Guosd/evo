package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.mq.rocketmq.assist.MessageHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.producer.RocketMQProducer;
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
	private static int count = 0;

	@Autowired
	private RocketMQProducer producer;

	public String send() {
		Message message = MessageHelper.createMessage("TopicTest", "TagA", MessageHelper.generateMessageKey(), "Hello RocketMQ Async " + count++);
		log.info("async send " + message);

		producer.send(message, new SendCallback() {
			@Override
			public void onSuccess(SendResult sendResult) {
				log.info("async result " + sendResult);
			}

			@Override
			public void onException(Throwable e) {
				log.error("发送 async 失败", e);
			}
		});

		return null;
	}
}
