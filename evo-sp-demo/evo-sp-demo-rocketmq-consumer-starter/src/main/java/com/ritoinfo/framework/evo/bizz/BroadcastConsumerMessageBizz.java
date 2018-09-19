package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.annotation.MQConsumer;
import com.maihaoche.starter.mq.base.AbstractMQPushConsumer;
import com.maihaoche.starter.mq.base.MessageExtConst;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-09-17 21:36
 */
@Slf4j
@MQConsumer(consumerGroup = "test_consumer_group", topic = "TopicTest", messageMode = MessageExtConst.MESSAGE_MODE_BROADCASTING)
public class BroadcastConsumerMessageBizz extends AbstractMQPushConsumer {
	@Override
	public boolean process(Object o, Map map) {
		log.info("broadcast consumer");
		log.info(o.toString());
		log.info(map.toString());
		return true;
	}
}
