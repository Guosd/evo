package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * User: Kyll
 * Date: 2018-09-25 16:25
 */
@Slf4j
@Service
public class RpcServerMessageBizz {
	private final static String QUEUE_NAME = "rpc";

	public String process() {
		Channel channel = CommonUtil.createChannel();
		CommonUtil.createQueue(channel, QUEUE_NAME, false);
		CommonUtil.queuePurge(channel, QUEUE_NAME);
		CommonUtil.basicQos(channel, 1);

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				AMQP.BasicProperties replyProps = new AMQP.BasicProperties
						.Builder()
						.correlationId(properties.getCorrelationId())
						.build();

				String response = "";

				try {
					String message = new String(body,"UTF-8");
					int n = Integer.parseInt(message);

					log.info("计算 fib(" + message + ")");
					response += fib(n);
				} catch (RuntimeException e){
					log.error("计算失败", e);
				} finally {
					CommonUtil.basicPublish(channel, "", properties.getReplyTo(), replyProps, response);

					channel.basicAck(envelope.getDeliveryTag(), false);
					// RabbitMq consumer worker thread notifies the RPC server owner thread
					synchronized(this) {
						this.notify();
					}
				}
			}
		};

		CommonUtil.basicConsume(channel, QUEUE_NAME, false, consumer);
		// Wait and be prepared to consume the message from RPC client.
		while (true) {
			synchronized(consumer) {
				try {
					consumer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static int fib(int n) {
		if (n ==0) return 0;
		if (n == 1) return 1;
		return fib(n-1) + fib(n-2);
	}
}
