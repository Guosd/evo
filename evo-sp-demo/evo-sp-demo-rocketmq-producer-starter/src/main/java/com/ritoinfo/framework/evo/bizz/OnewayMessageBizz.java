package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.base.MessageBuilder;
import com.ritoinfo.framework.evo.common.CommonUtil;
import com.ritoinfo.framework.evo.producer.CommonMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-13 17:21
 */
@Slf4j
@Service
public class OnewayMessageBizz {
	private static int count = 1;

	@Autowired
	private CommonMQProducer producer;

	public SendResult send() {
		log.info("oneway send " + count);

		producer.syncSend(MessageBuilder
				.of("Hello Oneway " + count)
				.topic("TopicTest")
				.tag("Tag_Oneway")
				.key(CommonUtil.generateMessageKey()).build());

		count++;
		return null;
	}
}
