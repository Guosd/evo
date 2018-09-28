package com.ritoinfo.framework.evo.mq.rocketmq.annotation;

import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Kyll
 * Date: 2018-09-28 13:25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RocketMQConsumer {
	String consumerGroup();
	String topic();
	String tags();
	ConsumeFromWhere consumeFromWhere() default ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET;
	MessageModel messageModel() default MessageModel.CLUSTERING;
}
