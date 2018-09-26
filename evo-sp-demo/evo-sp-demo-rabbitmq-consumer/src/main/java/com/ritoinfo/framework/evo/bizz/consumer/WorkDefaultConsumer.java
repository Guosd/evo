package com.ritoinfo.framework.evo.bizz.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.ritoinfo.framework.evo.common.CommonUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * User: Kyll
 * Date: 2018-09-21 16:48
 */
@Slf4j
public class WorkDefaultConsumer extends DefaultConsumer {
	private String name;

	public WorkDefaultConsumer(Channel channel) {
		super(channel);
	}

	public WorkDefaultConsumer(Channel channel, String name) {
		super(channel);
		this.name = name;
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
		String message = new String(body, StandardCharsets.UTF_8);
		log.info("接收 [" + message + "]");

		if (StringUtil.isNotBlank(name)) {
			log.info("消费者名称 " + name);
		}

		CommonUtil.doWork(message);

		log.info("消息处理完成");
	}
}
