package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * User: Kyll
 * Date: 2018-09-20 09:43
 */
@Slf4j
@Service
public class RecvMessageBizz {
	private final static String QUEUE_NAME = "hello";

	public void process() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");

		Connection connection;
		try {
			connection = factory.newConnection();
		} catch (IOException | TimeoutException e) {
			log.error("建立连接失败", e);
			return;
		}

		Channel channel;
		try {
			channel = connection.createChannel();
		} catch (IOException e) {
			log.error("建立通道失败", e);
			return;
		}

		boolean exit = false;
		try {
			AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			log.info(declareOk.toString());
		} catch (IOException e) {
			log.error("建立队列失败", e);
			exit = true;
		}

		if (!exit) {
			log.info("等待接收消息");

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					log.info("接收 " + message);
				}
			};

			try {
				channel.basicConsume(QUEUE_NAME, true, consumer);
			} catch (IOException e) {
				log.error("接收消息失败", e);
			}
		}
	}
}
