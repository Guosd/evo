package com.ritoinfo.framework.evo.dts.common.annotation;

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
public @interface RocketMQTransactionProducer {
	String namesrvAddr() default "";
	String group() default "";
	String topic() default "";
	String tags() default "";
	String target();
}
