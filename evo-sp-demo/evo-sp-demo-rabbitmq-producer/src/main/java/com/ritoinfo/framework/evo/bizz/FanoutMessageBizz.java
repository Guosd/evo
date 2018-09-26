package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.Channel;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-21 15:56
 */
@Slf4j
@Service
public class FanoutMessageBizz {
	private final static String EXCHANGE_NAME = "logs";

	public String process() {
		Channel channel = CommonUtil.createChannel();
		CommonUtil.createExchange(channel, EXCHANGE_NAME, "fanout");

		String message = CommonUtil.createMessage();

		log.info("发送 [" + message + "] Start");
		CommonUtil.basicPublish(channel, EXCHANGE_NAME, "", null, message);
		log.info("发送 [" + message + "] End");

		CommonUtil.closeAll(channel);

		return message;
	}
}
