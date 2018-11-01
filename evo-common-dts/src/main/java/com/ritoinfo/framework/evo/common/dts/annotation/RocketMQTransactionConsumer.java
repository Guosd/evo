package com.ritoinfo.framework.evo.common.dts.annotation;

import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Kyll
 * Date: 2018-09-28 13:25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RocketMQTransactionConsumer {
	String namesrvAddr() default "";
	String group() default "";
	String topic() default "";
	String tags() default "";
	Class messageListenerClass();
	ConsumeFromWhere consumeFromWhere() default ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;
	MessageModel messageModel() default MessageModel.CLUSTERING;
}
