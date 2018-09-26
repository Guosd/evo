package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.Channel;
import com.ritoinfo.framework.evo.bizz.consumer.AckDefaultConsumer;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-20 11:55
 */
@Slf4j
@Service
public class Work04MessageBizz {
	private final static String QUEUE_NAME = "hello";

	public void process() {
		Channel channel = CommonUtil.createChannel();
		CommonUtil.createQueue(channel, QUEUE_NAME, true);

		log.info("等待接收消息");
		CommonUtil.basicConsume(channel, QUEUE_NAME, false, new AckDefaultConsumer(channel));// 接收消息，但是不确认, 需要手工应答
	}
}
