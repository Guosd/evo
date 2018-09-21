package com.ritoinfo.framework.evo.common;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * User: Kyll
 * Date: 2018-09-20 10:39
 */
@Slf4j
public class CommonUtil {
	private static int count = 1;

	public static ConnectionFactory createConnectionFactory() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");

		return connectionFactory;
	}

	public static Connection createConnection(ConnectionFactory connectionFactory) {
		Connection connection = null;
		try {
			connection = connectionFactory.newConnection();
		} catch (IOException | TimeoutException e) {
			log.error("建立连接失败", e);
		}
		return connection;
	}

	public static Channel createChannel(Connection connection) {
		Channel channel = null;
		try {
			channel = connection.createChannel();
		} catch (IOException e) {
			log.error("建立通道失败", e);
		}
		return channel;
	}

	public static Channel createChannel() {
		ConnectionFactory connectionFactory = CommonUtil.createConnectionFactory();
		Connection connection = CommonUtil.createConnection(connectionFactory);
		return CommonUtil.createChannel(connection);
	}

	public static void createExchange(Channel channel, String exchange, String type) {
		try {
			AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchange, type);
			log.info(declareOk.toString());
		} catch (IOException e) {
			log.error("建立交换失败", e);
		}
	}

	public static void createQueue(Channel channel, String queueName, boolean durable) {
		assert channel != null;

		try {
			AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queueName, durable, false, false, null);
			log.info(declareOk.toString());
		} catch (IOException e) {
			log.error("建立队列失败", e);
		}
	}

	public static void basicPublish(Channel channel, String exchange, String queueName, AMQP.BasicProperties basicProperties, String message) {
		try {
			channel.basicPublish(exchange, queueName, basicProperties, message.getBytes());
		} catch (IOException e) {
			log.error("发送消息失败", e);
		}
	}

	public static void closeChannel(Channel channel) {
		try {
			channel.close();
		} catch (IOException | TimeoutException e) {
			log.error("关闭通道失败", e);
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (IOException e) {
			log.error("关闭连接失败", e);
		}
	}

	public static void closeAll(Channel channel) {
		Connection connection = channel.getConnection();
		closeChannel(channel);
		closeConnection(connection);
	}

	public static String createMessage() {
		StringBuilder message = new StringBuilder("Hello World " + count++);
		for (int i = 0, size = new Random().nextInt(10); i < size; i++) {
			message.append(".");
		}
		return message.toString();
	}
}
