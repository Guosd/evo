package com.ritoinfo.framework.evo.sp.demo.bizz;

import com.ritoinfo.framework.evo.dts.common.annotation.RocketMQTransactionConsumer;
import com.ritoinfo.framework.evo.dts.common.model.DtsMessage;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseMapperBizz;
import com.ritoinfo.framework.evo.sp.demo.dao.AccountDao;
import com.ritoinfo.framework.evo.sp.demo.dto.AccountDto;
import com.ritoinfo.framework.evo.sp.demo.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * User: Kyll
 * Date: 2018-07-12 13:13
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class AccountBizz extends BaseMapperBizz<AccountDao, Account, Long, AccountDto> {
	/**
	 * 接收事务消息
	 * 参数有两种形式：
	 * List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext
	 * list 接收到的 RocketMQ 原始消息列表
	 * consumeConcurrentlyContext 消费者并行上下文
	 *
	 * DtsMessage dtsMessage 从 MessageExt 转换而成的事务消息，此对象中包含事务信息
	 * @param dtsMessage
	 * @return
	 */
	@RocketMQTransactionConsumer(messageListenerClass = MessageListenerConcurrently.class)
	public ConsumeConcurrentlyStatus consumeMessage(DtsMessage dtsMessage) {
		this.saveLoacl();

		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

	@Transactional
	public void saveLoacl() {
		AccountDto accountDto = this.get(1L);
		accountDto.setAmount(accountDto.getAmount().subtract(BigDecimal.valueOf(100)));
		this.update(accountDto);
	}
}
