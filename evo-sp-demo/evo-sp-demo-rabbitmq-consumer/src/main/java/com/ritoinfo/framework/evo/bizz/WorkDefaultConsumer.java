package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * User: Kyll
 * Date: 2018-09-21 16:48
 */
@Slf4j
public class WorkDefaultConsumer extends DefaultConsumer {
	public WorkDefaultConsumer(Channel channel) {
		super(channel);
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
		String message = new String(body, StandardCharsets.UTF_8);
		log.info("接收 [" + message + "]");

		for (char ch: message.toCharArray()) {
			if (ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					log.error("消息处理失败", e);
				}
			}
		}
	}
}
