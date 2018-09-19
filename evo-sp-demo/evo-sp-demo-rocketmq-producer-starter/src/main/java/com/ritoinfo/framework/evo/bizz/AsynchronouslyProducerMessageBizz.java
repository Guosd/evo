package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.base.MessageBuilder;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-12 16:56
 */
@Service
public class AsynchronouslyProducerMessageBizz {
	@Autowired
	private AsynchronouslyProducer producer;

	public void send() {
		for (int i = 0; i < 1; i++) {
			System.out.println("async send " + i);

			Message message = MessageBuilder.of("Hello Async " + i).topic("TopicTest").tag("TagA").key("OrderID188").build();
			producer.syncSend(message);

			/*producer.asyncSend(message, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.println(sendResult);
				}

				@Override
				public void onException(Throwable throwable) {
					throwable.printStackTrace();
				}
			});*/
		}

	}
}
