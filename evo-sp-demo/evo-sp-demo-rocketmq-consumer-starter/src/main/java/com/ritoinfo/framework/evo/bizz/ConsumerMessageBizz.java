package com.ritoinfo.framework.evo.bizz;

import com.maihaoche.starter.mq.annotation.MQConsumer;
import com.maihaoche.starter.mq.base.AbstractMQPushConsumer;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-09-19 15:59
 */
@MQConsumer(consumerGroup = "test_consumer_group", topic = "TopicTest")
public class ConsumerMessageBizz extends AbstractMQPushConsumer {
	@Override
	public boolean process(Object o, Map map) {
		System.out.println(o);
		System.out.println(map);
		return true;
	}
}
