package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.common.Config;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-17 22:30
 */
@Service
public class ScheduleProducerMessageBizz {
	public void send() {
		DefaultMQProducer producer = new DefaultMQProducer("test_schedule_group");
		producer.setNamesrvAddr(Config.NAMESRV_ADDR);

		try {
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		for (int i = 0; i < 1; i++) {
			Message message = new Message("TopicTest", ("Hello scheduled message " + i).getBytes());
			message.setDelayTimeLevel(3);

			System.out.println("schedule send " + i);

			SendResult sendResult = null;
			try {
				sendResult = producer.send(message);
			} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
				e.printStackTrace();
			}

			System.out.printf("%s%n", sendResult);
		}

		producer.shutdown();
	}
}
