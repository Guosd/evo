package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.Channel;
import com.ritoinfo.framework.evo.bizz.consumer.WorkDefaultConsumer;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-25 09:06
 */
@Slf4j
@Service
public class Receive02MessageBizz {
	private final static String EXCHANGE_NAME = "logs";

	public void process() {
		Channel channel = CommonUtil.createChannel();
		CommonUtil.createExchange(channel, EXCHANGE_NAME, "fanout");

		String queueName = CommonUtil.getQueue(channel);
		CommonUtil.queueBind(channel, queueName, EXCHANGE_NAME, "");

		log.info("等待接收消息");
		CommonUtil.basicConsume(channel, queueName, true, new WorkDefaultConsumer(channel));
	}
}
