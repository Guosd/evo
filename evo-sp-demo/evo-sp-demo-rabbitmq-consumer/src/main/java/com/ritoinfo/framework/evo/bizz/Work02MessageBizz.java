package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
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
public class Work02MessageBizz {
	private final static String QUEUE_NAME = "hello_durable";

	public void process() {
		ConnectionFactory connectionFactory = CommonUtil.createConnectionFactory();
		Connection connection = CommonUtil.createConnection(connectionFactory);
		Channel channel = CommonUtil.createChannel(connection);

	//	AMQP.Queue.DeclareOk declareOk = CommonUtil.createQueue(channel, QUEUE_NAME);

		log.info("等待接收消息");
		try {
			channel.basicQos(1);// 一个处理一个消息
		} catch (IOException e) {
			log.error("设置服务质量失败", e);
			return;
		}

		final Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				log.info("接收 " + message);

				try {
					doWork(message);
				} catch (Exception e) {
					log.error("消息处理失败", e);
				} finally {
					log.info("消息处理完成");
				}
			}
		};

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
