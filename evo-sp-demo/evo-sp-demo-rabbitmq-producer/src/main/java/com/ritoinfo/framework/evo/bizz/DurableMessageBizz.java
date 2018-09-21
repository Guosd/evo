package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-21 10:47
 */
@Slf4j
@Service
public class DurableMessageBizz {
	private final static String QUEUE_NAME = "hello_durable";

	public String process() {
		Channel channel = CommonUtil.createChannel();

		CommonUtil.createQueue(channel, QUEUE_NAME, true);
		String message = CommonUtil.createMessage();

		log.info("发送 [" + message + "] Start");
		CommonUtil.basicPublish(channel, "", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message);
		log.info("发送 [" + message + "] End");

		CommonUtil.closeAll(channel);

		return message;
	}
}
