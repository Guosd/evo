package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.Channel;
import com.ritoinfo.framework.evo.bizz.consumer.WorkDefaultConsumer;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-20 11:55
 */
@Slf4j
@Service
public class Work02MessageBizz {
	private final static String QUEUE_NAME = "hello_durable";

	public void process() {
		Channel channel = CommonUtil.createChannel();
		CommonUtil.createQueue(channel, QUEUE_NAME, true);

		CommonUtil.basicQos(channel, 1);// 一个处理一个消息

		log.info("等待接收消息");
		CommonUtil.basicConsume(channel, QUEUE_NAME, true, new WorkDefaultConsumer(channel));
	}
}
