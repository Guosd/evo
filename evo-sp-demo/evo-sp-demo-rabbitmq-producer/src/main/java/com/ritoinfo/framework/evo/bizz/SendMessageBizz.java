package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.Channel;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-20 08:43
 */
@Slf4j
@Service
public class SendMessageBizz {
	private final static String QUEUE_NAME = "hello";

	public String process() {
		Channel channel = CommonUtil.createChannel();

		CommonUtil.createQueue(channel, QUEUE_NAME, false);
		String message = CommonUtil.createMessage();

		log.info("发送 [" + message + "] Start");
		CommonUtil.basicPublish(channel, "", QUEUE_NAME, null, message);
		log.info("发送 [" + message + "] End");

		CommonUtil.closeAll(channel);

		return message;
	}


}
