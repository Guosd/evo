package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * User: Kyll
 * Date: 2018-09-25 16:25
 */
@Slf4j
@Service
public class RpcClientMessageBizz {
	private final static String QUEUE_NAME = "rpc";

	public String process() {
		Channel channel = CommonUtil.createChannel();

		final String corrId = UUID.randomUUID().toString();
		String replyQueueName = CommonUtil.getQueue(channel);
		AMQP.BasicProperties props = new AMQP.BasicProperties
				.Builder()
				.correlationId(corrId)
				.replyTo(replyQueueName)
				.build();

		CommonUtil.basicPublish(channel, "", QUEUE_NAME, props, String.valueOf(new Random().nextInt(10) + 1));

		final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
		String ctag = CommonUtil.basicConsume(channel, replyQueueName, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				if (properties.getCorrelationId().equals(corrId)) {
					response.offer(new String(body, "UTF-8"));
				}
			}
		});

		String result = null;
		try {
			result = response.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			channel.basicCancel(ctag);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CommonUtil.closeAll(channel);

		return result;
	}
}
