package com.ritoinfo.framework.evo.bizz;

import com.rabbitmq.client.Channel;
import com.ritoinfo.framework.evo.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * User: Kyll
 * Date: 2018-09-21 15:56
 */
@Slf4j
@Service
public class TopicMessageBizz {
	private final static String EXCHANGE_NAME = "topic_logs";

	public String process() {
		Channel channel = CommonUtil.createChannel();
		CommonUtil.createExchange(channel, EXCHANGE_NAME, "topic");

		String[] facilities = {"auth", "cron", "kern"};
		String[] severities = {"info", "warn", "error"};
		String routeKey = facilities[new Random().nextInt(facilities.length)] + "." + severities[new Random().nextInt(severities.length)];

		String message = CommonUtil.createMessage(routeKey);

		log.info("发送 [" + message + "] Start");
		CommonUtil.basicPublish(channel, EXCHANGE_NAME, routeKey, null, message);
		log.info("发送 [" + message + "] End");

		CommonUtil.closeAll(channel);

		return message;
	}
}
