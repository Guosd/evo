package com.ritoinfo.framework.evo.common.dts.aspect;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.config.properties.ApplicationProperties;
import com.ritoinfo.framework.evo.common.dts.assist.DtsHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.assist.RocketMQHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.config.properties.RocketMQProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.producer.RocketMQProducer;
import com.ritoinfo.framework.evo.sp.dts.dto.DtsBizzMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-10-11 09:23
 */
@Slf4j
@Aspect
@Component
public class RocketMQTransactionConsumerAspect {
	private static final Map<String, DefaultMQPushConsumer> DEFAULT_MQ_PUSH_CONSUMER_MAP = new HashMap<>();

	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private RocketMQProperties rocketMQProperties;
	@Autowired
	private RocketMQProducer rocketMQProducer;

	@Pointcut("@annotation(com.ritoinfo.framework.evo.dts.common.annotation.RocketMQTransactionConsumer)")
	public void rocketMQTransactionConsumerPointcut() {
	}

	@Around(value = "rocketMQTransactionConsumerPointcut()")
	public Object rocketMQTransactionConsumerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		DtsBizzMessageDto dtsBizzMessageDto = (DtsBizzMessageDto) proceedingJoinPoint.getArgs()[0];

		log.info("发送 transaction log 处理中消息成功 {}", rocketMQProducer.send(DtsHelper.createDtsLogMessage(rocketMQProperties, RocketMQHelper.generateMessageKey(applicationProperties.getApplicationName()), dtsBizzMessageDto, Const.DTS_ROLE_CONSUMER, Const.DTS_STEP_PROCESS)));

		ConsumeConcurrentlyStatus consumeConcurrentlyStatus;
		try {
			log.info("本地 transaction 执行开始 {}", dtsBizzMessageDto);
			consumeConcurrentlyStatus = (ConsumeConcurrentlyStatus) proceedingJoinPoint.proceed(new Object[]{dtsBizzMessageDto});
			log.info("本地 transaction 执行结果 {}", consumeConcurrentlyStatus);
		} catch (Throwable throwable) {
			log.error("本地 transaction 执行失败", throwable);
			consumeConcurrentlyStatus = ConsumeConcurrentlyStatus.RECONSUME_LATER;
		}

		if (ConsumeConcurrentlyStatus.CONSUME_SUCCESS == consumeConcurrentlyStatus) {
			log.info("发送 transaction log 已完成消息成功 {}", rocketMQProducer.send(DtsHelper.createDtsLogMessage(rocketMQProperties, RocketMQHelper.generateMessageKey(applicationProperties.getApplicationName()), dtsBizzMessageDto, Const.DTS_ROLE_CONSUMER, Const.DTS_STEP_FINISHED)));
		}

		return consumeConcurrentlyStatus;
	}
}
