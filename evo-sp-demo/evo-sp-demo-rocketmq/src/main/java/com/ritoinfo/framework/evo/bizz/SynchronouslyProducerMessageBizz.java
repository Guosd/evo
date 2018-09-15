package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.common.Config;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * User: Kyll
 * Date: 2018-09-12 16:38
 */
@Service
public class SynchronouslyProducerMessageBizz {
	public void send() {
		DefaultMQProducer producer = new DefaultMQProducer("test_sync_group");
		producer.setNamesrvAddr(Config.NAMESRV_ADDR);
	//	producer.setNamesrvAddr("127.0.0.1:9876");
		//	producer.setSendMsgTimeout(10000);
		//	producer.setVipChannelEnabled(false);

		try {
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		for (int i = 0; i < 1; i++) {
			Message msg = null;
			try {
				msg = new Message(
						"TopicTest" /* Topic */,
						"TagA" /* Tag */,
						("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
				);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (msg != null) {
				System.out.println("sync send " + i);

				SendResult sendResult = null;
				try {
					sendResult = producer.send(msg);
				} catch (MQClientException | RemotingException | InterruptedException | MQBrokerException e) {
					e.printStackTrace();
				}

				System.out.printf("%s%n", sendResult);
			}
		}

		producer.shutdown();
	}
}
