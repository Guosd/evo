package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * User: Kyll
 * Date: 2018-09-20 11:55
 */
@Slf4j
@Service
public class Work01MessageBizz {
	private final static String QUEUE_NAME = "hello";

	public void process() {
		Channel channel = CommonUtil.createChannel();
		CommonUtil.createQueue(channel, QUEUE_NAME, true);

		log.info("等待接收消息");
		Consumer consumer = new WorkDefaultConsumer(channel);

		boolean autoAck = true; // acknowledgment is covered below
		try {
			channel.basicConsume(QUEUE_NAME, autoAck, consumer);
		} catch (IOException e) {
			log.error("接收消息失败", e);
		}
	}

	private static void doWork(String task) throws InterruptedException {
		for (char ch: task.toCharArray()) {
			if (ch == '.') Thread.sleep(1000);
		}
	}
}
