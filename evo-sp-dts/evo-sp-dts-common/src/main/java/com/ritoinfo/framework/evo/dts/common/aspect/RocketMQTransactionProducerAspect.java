package com.ritoinfo.framework.evo.dts.common.aspect;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.config.properties.ApplicationProperties;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.ConcurrentUtil;
import com.ritoinfo.framework.evo.common.uitl.JsonUtil;
import com.ritoinfo.framework.evo.dts.common.DtsTransaction;
import com.ritoinfo.framework.evo.dts.common.annotation.RocketMQTransactionProducer;
import com.ritoinfo.framework.evo.dts.common.assist.DtsHelper;
import com.ritoinfo.framework.evo.dts.common.model.DtsMessage;
import com.ritoinfo.framework.evo.mq.rocketmq.assist.RocketMQHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.config.properties.RocketMQProperties;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import com.ritoinfo.framework.evo.mq.rocketmq.producer.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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
public class RocketMQTransactionProducerAspect {
	private static final Map<String, TransactionMQProducer> TRANSACTION_MQ_PRODUCER_MAP = new HashMap<>();

	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private RocketMQProperties rocketMQProperties;
	@Autowired
	private RocketMQProducer rocketMQProducer;

	@Pointcut("@annotation(com.ritoinfo.framework.evo.dts.common.annotation.RocketMQTransactionProducer)")
	public void rocketMQTransactionProducerPointcut() {
	}

	@Around(value = "rocketMQTransactionProducerPointcut()")
	public Object rocketMQTransactionProducerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		RocketMQTransactionProducer rocketMQTransactionProducer = BeanUtil.getAnnotation(proceedingJoinPoint, RocketMQTransactionProducer.class);
		String namesrvAddr = DtsHelper.getProducerNamesrvAddr(rocketMQProperties, rocketMQTransactionProducer);

		TransactionMQProducer transactionMQProducer = TRANSACTION_MQ_PRODUCER_MAP.get(namesrvAddr);// 一个 mqnamesrv 一个 producer
		if (transactionMQProducer == null) {
			transactionMQProducer = new TransactionMQProducer(DtsHelper.getProducerGroup(rocketMQProperties, rocketMQTransactionProducer));
			transactionMQProducer.setInstanceName(RocketMQHelper.createInstanceName(applicationProperties.getApplicationName()));
			transactionMQProducer.setNamesrvAddr(DtsHelper.getProducerNamesrvAddr(rocketMQProperties, rocketMQTransactionProducer));
			transactionMQProducer.setExecutorService(ConcurrentUtil.createExecutorService(rocketMQProperties.getCorePoolSize(), rocketMQProperties.getMaximumPoolSize(), rocketMQProperties.getKeepAliveTime(), rocketMQProperties.getCapacity(), rocketMQProperties.getThreadName()));
			transactionMQProducer.setTransactionListener(new TransactionListener() {
				@Override
				public LocalTransactionState executeLocalTransaction(Message message, Object o) {
					LocalTransactionState localTransactionState;
					try {
						log.info("本地 transaction 执行开始 Message = {}, Object = {}", message, o);
						localTransactionState = (LocalTransactionState) proceedingJoinPoint.proceed(getArgs(proceedingJoinPoint, message, o));
						log.info("本地 transaction 执行结果 {}", localTransactionState);
					} catch (Throwable throwable) {
						log.error("本地 transaction 执行失败", throwable);
						localTransactionState = LocalTransactionState.ROLLBACK_MESSAGE;
					}
					return localTransactionState;
				}

				@Override
				public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
					log.info("本地 transaction 检查开始 messageExt = {}", messageExt);
					LocalTransactionState localTransactionState = ((DtsTransaction) proceedingJoinPoint.getTarget()).checkLocal(messageExt);
					log.info("本地 transaction 检查结果 {}", localTransactionState);
					return localTransactionState;
				}
			});

			try {
				transactionMQProducer.start();
			} catch (MQClientException e) {
				throw new RocketMQOperateException("启动 TransactionMQProducer 失败", e);
			}

			TRANSACTION_MQ_PRODUCER_MAP.put(namesrvAddr, transactionMQProducer);
		}

		DtsMessage dtsMessage = new DtsMessage();
		dtsMessage.setMessageKey(RocketMQHelper.generateMessageKey(applicationProperties.getApplicationName()));
		dtsMessage.setBusinessKey(getBusinessKey(proceedingJoinPoint.getTarget(), rocketMQProperties, rocketMQTransactionProducer));
		dtsMessage.setSource(applicationProperties.getApplicationName());
		dtsMessage.setTarget(rocketMQTransactionProducer.target());

		Object arg = getArg(proceedingJoinPoint.getTarget(), rocketMQProperties, rocketMQTransactionProducer);
		if (arg != null) {
			dtsMessage.setArg(JsonUtil.objectToJson(arg));
		}

		rocketMQProducer.send(DtsHelper.createDtsLogMessage(rocketMQProperties, RocketMQHelper.generateMessageKey(applicationProperties.getApplicationName()), dtsMessage, Const.DTS_ROLE_PRODUCER, Const.DTS_STEP_PROCESS));

		TransactionSendResult transactionSendResult;
		try {
			transactionSendResult = transactionMQProducer.sendMessageInTransaction(DtsHelper.createDtsMessage(rocketMQProperties, rocketMQTransactionProducer, dtsMessage), arg);
			log.info("发送 transaction 消息成功 {}", transactionSendResult);
		} catch (MQClientException e) {
			throw new RocketMQOperateException("发送 transaction 消息失败", e);
		}

		if (LocalTransactionState.COMMIT_MESSAGE == transactionSendResult.getLocalTransactionState()) {
			rocketMQProducer.send(DtsHelper.createDtsLogMessage(rocketMQProperties, RocketMQHelper.generateMessageKey(applicationProperties.getApplicationName()), dtsMessage, Const.DTS_ROLE_PRODUCER, Const.DTS_STEP_FINISHED));
		}

		return null;
	}

	private Object[] getArgs(ProceedingJoinPoint proceedingJoinPoint, Message message, Object o) {
		Object[] args = proceedingJoinPoint.getArgs();

		Class[] parameterTypes = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
		if (parameterTypes.length > 0) {
			if (parameterTypes[parameterTypes.length - 1] == Message.class) {
				args[parameterTypes.length - 1] = message;
			} else if (parameterTypes.length > 1 && parameterTypes[parameterTypes.length - 2] == Message.class && parameterTypes[parameterTypes.length - 1] == Object.class) {
				args[parameterTypes.length - 2] = message;
				args[parameterTypes.length - 1] = o;
			}
		}

		return args;
	}

	private String getBusinessKey(Object target, RocketMQProperties rocketMQProperties, RocketMQTransactionProducer rocketMQTransactionProducer) {
		return ((DtsTransaction) target).getBusinessKey(DtsHelper.getProducerNamesrvAddr(rocketMQProperties, rocketMQTransactionProducer), DtsHelper.getProducerGroup(rocketMQProperties, rocketMQTransactionProducer), DtsHelper.getProducerTopic(rocketMQProperties, rocketMQTransactionProducer), DtsHelper.getProducerTags(rocketMQProperties, rocketMQTransactionProducer), rocketMQTransactionProducer.target(), applicationProperties.getApplicationName());
	}

	private Object getArg(Object target, RocketMQProperties rocketMQProperties, RocketMQTransactionProducer rocketMQTransactionProducer) {
		return ((DtsTransaction) target).getArg(DtsHelper.getProducerNamesrvAddr(rocketMQProperties, rocketMQTransactionProducer), DtsHelper.getProducerGroup(rocketMQProperties, rocketMQTransactionProducer), DtsHelper.getProducerTopic(rocketMQProperties, rocketMQTransactionProducer), DtsHelper.getProducerTags(rocketMQProperties, rocketMQTransactionProducer), rocketMQTransactionProducer.target(), applicationProperties.getApplicationName());
	}
}
