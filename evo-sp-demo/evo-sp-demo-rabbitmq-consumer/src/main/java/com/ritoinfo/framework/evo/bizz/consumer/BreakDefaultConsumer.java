package com.ritoinfo.framework.evo.bizz.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * User: Kyll
 * Date: 2018-09-25 08:36
 */
@Slf4j
public class BreakDefaultConsumer extends DefaultConsumer {
	public BreakDefaultConsumer(Channel channel) {
		super(channel);
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
		String message = new String(body, StandardCharsets.UTF_8);
		log.info("接收 [" + message + "]");

		if (message.contains("7")) {
			CommonUtil.closeAll(this.getChannel());
			return;
		}

		CommonUtil.doWork(message);

		log.info("消息处理完成");
	}
}
