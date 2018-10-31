package com.ritoinfo.framework.evo.sp.demo.consumer;

import com.ritoinfo.framework.evo.dts.common.assist.DtsHelper;
import com.ritoinfo.framework.evo.dts.common.exception.DtsException;
import com.ritoinfo.framework.evo.dts.common.model.DtsBizzMessageDto;
import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import com.ritoinfo.framework.evo.sp.demo.bizz.AccountBizz;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-10-20 20:09
 */
@RocketMQConsumer
@Slf4j
@Component
public class DtsMessageConsumer implements MessageListenerConcurrently {
	@Autowired
	private AccountBizz accountBizz;

	@Transactional
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		log.info("接收消息 List<MessageExt> = {}，ConsumeConcurrentlyContext = {}", list, consumeConcurrentlyContext);

		List<DtsBizzMessageDto> dtsBizzMessageDtoList = DtsHelper.parseDtsMessage(list);
		if (dtsBizzMessageDtoList.size() > 1) {
			throw new DtsException("不支持批量获取消息");
		}

		return accountBizz.consumeMessage(dtsBizzMessageDtoList.get(0));
	}
}
