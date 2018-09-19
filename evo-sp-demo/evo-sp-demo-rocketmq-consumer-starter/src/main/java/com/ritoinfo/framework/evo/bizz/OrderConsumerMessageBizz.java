package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.annotation.MQConsumer;
import com.maihaoche.starter.mq.base.AbstractMQPushConsumer;
import com.maihaoche.starter.mq.base.MessageExtConst;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-09-19 18:15
 */
@Slf4j
@MQConsumer(consumerGroup = "test_consumer_group", topic = "TopicTest", consumeMode = MessageExtConst.CONSUME_MODE_ORDERLY)
public class OrderConsumerMessageBizz extends AbstractMQPushConsumer {
	@Override
	public boolean process(Object o, Map map) {
		log.info("order consumer");
		log.info(o.toString());
		log.info(map.toString());
		return true;
	}
}
