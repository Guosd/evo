package com.ritoinfo.framework.evo.bizz;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * User: Kyll
 * Date: 2018-09-12 16:56
 */
@Service
public class AsynchronouslyProducerMessageBizz {
	public void send() {
		DefaultMQProducer producer = new DefaultMQProducer("test_async_group");
		producer.setInstanceName("AsyncProducer");
	//	producer.setNamesrvAddr("192.168.1.141:9876");
		producer.setNamesrvAddr("192.168.1.141:9876");
		producer.setSendMsgTimeout(10000);
		producer.setVipChannelEnabled(false);
		producer.setClientIP("192.168.1.14");

		try {
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		/*try {
			producer.createTopic(producer.getCreateTopicKey(), "TopicAsyncTest", producer.getDefaultTopicQueueNums());
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}*/

		producer.setRetryTimesWhenSendAsyncFailed(0);

		for (int i = 0; i < 1; i++) {
			Message msg = null;
			try {
				msg = new Message(
						"TopicTest",
						"TagA",
						"OrderID188",
						("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (msg != null) {
				System.out.println("async send " + i);

				try {
					producer.send(msg, new SendCallback() {
						@Override
						public void onSuccess(SendResult sendResult) {
							System.out.printf("%s%n", sendResult);
						}
						@Override
						public void onException(Throwable e) {
							e.printStackTrace();
						}
					});
				} catch (MQClientException | RemotingException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		producer.shutdown();
	}
}
