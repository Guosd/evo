package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.base.MessageBuilder;
import com.maihaoche.starter.mq.enums.DelayTimeLevel;
import com.ritoinfo.framework.evo.common.CommonUtil;
import com.ritoinfo.framework.evo.producer.CommonMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-09-17 22:30
 */
@Slf4j
@Service
public class ScheduleMessageBizz {
	private static int count = 1;

	@Autowired
	private CommonMQProducer producer;

	public SendResult send() {
		log.info("schedule send " + count);

		producer.syncSend(MessageBuilder
				.of("Hello Schedule " + count)
				.topic("TopicTest")
				.tag("Tag_Schedule")
				.key(CommonUtil.generateMessageKey())
				.delayTimeLevel(DelayTimeLevel.SECOND_10).build());

		count++;
		return null;
	}
}
