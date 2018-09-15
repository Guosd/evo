package com.ritoinfo.framework.evo.bizz;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * User: Kyll
 * Date: 2018-09-13 18:07
 */
@Service
public class OrderProducerMessageBizz {
	public void send() {
		MQProducer producer = new DefaultMQProducer("test_order_group");

		try {
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
		for (int i = 0; i < 1; i++) {
			int orderId = i % 10;
			Message msg = null;
			try {
				msg = new Message(
						"TopicTest",
						tags[i % tags.length], "KEY" + i,
						("Hello RocketMQ Order " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (msg != null) {
				System.out.println("order send " + i);

				SendResult sendResult = null;
				try {
					sendResult = producer.send(msg, (mqs, msg1, arg) -> {
						Integer id = (Integer) arg;
						int index = id % mqs.size();
						return mqs.get(index);
					}, orderId);
				} catch (MQClientException | RemotingException | InterruptedException | MQBrokerException e) {
					e.printStackTrace();
				}

				System.out.printf("%s%n", sendResult);
			}
		}

		producer.shutdown();
	}
}
