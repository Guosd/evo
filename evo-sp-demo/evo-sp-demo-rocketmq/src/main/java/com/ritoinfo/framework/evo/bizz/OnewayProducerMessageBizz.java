package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.common.Config;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * User: Kyll
 * Date: 2018-09-13 17:21
 */
@Service
public class OnewayProducerMessageBizz {
	public void send() {
		DefaultMQProducer producer = new DefaultMQProducer("test_oneway_group");
		producer.setNamesrvAddr(Config.NAMESRV_ADDR);

		try {
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		for (int i = 0; i < 1; i++) {
			Message msg = null;
			try {
				msg = new Message("TopicTest" /* Topic */,
						"TagA" /* Tag */,
						("Hello RocketMQ Oneway " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
				);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (msg != null) {
				System.out.println("oneway send " + i);

				try {
					producer.sendOneway(msg);
				} catch (MQClientException | RemotingException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		//Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}
}
