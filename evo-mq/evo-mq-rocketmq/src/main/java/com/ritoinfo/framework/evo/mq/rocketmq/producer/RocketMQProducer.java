package com.ritoinfo.framework.evo.mq.rocketmq.producer;

import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-09-28 16:15
 */
@Slf4j
@Component
public class RocketMQProducer {
	@Qualifier("defaultMQProducer")
	@Autowired
	private DefaultMQProducer defaultMQProducer;

	public SendResult send(Message message) {
		try {
			return defaultMQProducer.send(message);
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
			throw new RocketMQOperateException("发送 Sync 消息失败", e);
		}
	}

	public void send(Message message, SendCallback sendCallback) {
		try {
			defaultMQProducer.send(message, sendCallback);
		} catch (MQClientException | RemotingException | InterruptedException e) {
			throw new RocketMQOperateException("发送 Async 消息失败", e);
		}
	}

	public void sendOneway(Message message) {
		try {
			defaultMQProducer.sendOneway(message);
		} catch (MQClientException | RemotingException | InterruptedException e) {
			throw new RocketMQOperateException("发送 Oneway 消息失败", e);
		}
	}

	public SendResult sendBroadcast(Message message) {
		try {
			return defaultMQProducer.send(message);
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
			throw new RocketMQOperateException("发送 Broadcast 消息失败", e);
		}
	}

	public SendResult send(Message message, MessageQueueSelector messageQueueSelector, Object arg) {
		try {
			return defaultMQProducer.send(message, messageQueueSelector, arg);
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
			throw new RocketMQOperateException("发送 Order 消息失败", e);
		}
	}

	public SendResult sendSchedule(Message message) {
		try {
			return defaultMQProducer.send(message);
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
			throw new RocketMQOperateException("发送 Schedule 消息失败", e);
		}
	}
}
