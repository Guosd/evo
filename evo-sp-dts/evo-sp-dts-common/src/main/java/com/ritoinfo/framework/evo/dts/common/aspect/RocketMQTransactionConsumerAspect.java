package com.ritoinfo.framework.evo.dts.common.aspect;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.config.properties.ApplicationProperties;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.dts.common.annotation.RocketMQTransactionConsumer;
import com.ritoinfo.framework.evo.dts.common.assist.DtsHelper;
import com.ritoinfo.framework.evo.dts.common.exception.DtsException;
import com.ritoinfo.framework.evo.dts.common.model.DtsMessage;
import com.ritoinfo.framework.evo.mq.rocketmq.assist.RocketMQHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.config.properties.RocketMQProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import com.ritoinfo.framework.evo.mq.rocketmq.producer.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
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
		RocketMQTransactionConsumer rocketMQTransactionConsumer = BeanUtil.getAnnotation(proceedingJoinPoint, RocketMQTransactionConsumer.class);
		String signature = proceedingJoinPoint.getSignature().toString();

		DefaultMQPushConsumer defaultMQPushConsumer = DEFAULT_MQ_PUSH_CONSUMER_MAP.get(signature);// 一个 RocketMQTransactionConsumer 注解，一个 consumer
		if (defaultMQPushConsumer == null) {
			DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(DtsHelper.getConsumerGroup(rocketMQProperties, rocketMQTransactionConsumer));
			consumer.setNamesrvAddr(DtsHelper.getConsumerNamesrvAddr(rocketMQProperties, rocketMQTransactionConsumer));
			consumer.setConsumeFromWhere(rocketMQTransactionConsumer.consumeFromWhere());
			consumer.setMessageModel(rocketMQTransactionConsumer.messageModel());

			try {
				consumer.subscribe(DtsHelper.getConsumerTopic(rocketMQProperties, rocketMQTransactionConsumer), DtsHelper.getConsumerTags(rocketMQProperties, rocketMQTransactionConsumer));
			} catch (MQClientException e) {
				throw new RocketMQOperateException("订阅消息失败", e);
			}

			Class clazz = rocketMQTransactionConsumer.messageListenerClass();
			if (MessageListenerConcurrently.class == clazz) {
				consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
					log.info("接收 transaction 消息 List<MessageExt> = {}，ConsumeConcurrentlyContext = {}", list, consumeConcurrentlyContext);

					List<DtsMessage> dtsMessageList = DtsHelper.parseDtsMessage(list);
					if (dtsMessageList.size() > 1) {
						throw new DtsException("不支持批量获取消息");
					}

					DtsMessage dtsMessage = dtsMessageList.get(0);

					rocketMQProducer.send(DtsHelper.createDtsLogMessage(rocketMQProperties, RocketMQHelper.generateMessageKey(applicationProperties.getApplicationName()), dtsMessage, Const.DTS_ROLE_CONSUMER, Const.DTS_STEP_PROCESS));

					ConsumeConcurrentlyStatus consumeConcurrentlyStatus;
					try {
						log.info("本地 transaction 执行开始 DtsMessage = {}", dtsMessage);
						consumeConcurrentlyStatus = (ConsumeConcurrentlyStatus) proceedingJoinPoint.proceed(getArgs(proceedingJoinPoint, dtsMessage, list, consumeConcurrentlyContext));
						log.info("本地 transaction 执行结果 {}", consumeConcurrentlyStatus);
					} catch (Throwable throwable) {
						log.error("本地 transaction 执行失败", throwable);
						consumeConcurrentlyStatus = ConsumeConcurrentlyStatus.RECONSUME_LATER;
					}

					if (ConsumeConcurrentlyStatus.CONSUME_SUCCESS == consumeConcurrentlyStatus) {
						rocketMQProducer.send(DtsHelper.createDtsLogMessage(rocketMQProperties, RocketMQHelper.generateMessageKey(applicationProperties.getApplicationName()), dtsMessage, Const.DTS_ROLE_CONSUMER, Const.DTS_STEP_FINISHED));
					}

					return consumeConcurrentlyStatus;
				});
			} else if (MessageListenerOrderly.class == clazz) {
				consumer.registerMessageListener((MessageListenerOrderly) (list, consumeOrderlyContext) -> {
					throw new DtsException("不支持 MessageListenerOrderly 监听");
				});
			} else {
				throw new RocketMQOperateException("不支持的 MessageListener " + clazz);
			}

			try {
				consumer.start();
			} catch (MQClientException e) {
				throw new RocketMQOperateException("启动 DefaultMQPushConsumer 失败 " + signature, e);
			}

			DEFAULT_MQ_PUSH_CONSUMER_MAP.put(signature, consumer);
		}

		return null;
	}

	private Object[] getArgs(ProceedingJoinPoint proceedingJoinPoint, DtsMessage dtsMessage, List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		Object[] args = proceedingJoinPoint.getArgs();

		Class[] parameterTypes = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
		if (parameterTypes.length > 0) {
			if (parameterTypes[parameterTypes.length - 1] == DtsMessage.class) {
				args[parameterTypes.length - 1] = dtsMessage;
			} else if (parameterTypes.length > 1 && parameterTypes[parameterTypes.length - 2] == List.class && parameterTypes[parameterTypes.length - 1] == ConsumeConcurrentlyContext.class) {
				args[parameterTypes.length - 2] = list;
				args[parameterTypes.length - 1] = consumeConcurrentlyContext;
			}
		}

		return args;
	}
}
