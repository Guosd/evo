package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.mq.rocketmq.annotation.RocketMQConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Kyll
 * Date: 2018-09-17 21:50
 */
@RocketMQConsumer(consumerGroup = "test_order_group", topic = "TopicTest", tags = "TagA || TagC || TagD", consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, messageModel = MessageModel.BROADCASTING)
@Slf4j
@Service
public class OrderConsumerMessageBizz implements MessageListenerOrderly {
	private AtomicLong consumeTimes = new AtomicLong(0);

	@Override
	public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
		log.info("Receive " + list + ", " + consumeOrderlyContext);

		consumeOrderlyContext.setAutoCommit(false);

		this.consumeTimes.incrementAndGet();
		if ((this.consumeTimes.get() % 2) == 0) {
			return ConsumeOrderlyStatus.SUCCESS;
		} else if ((this.consumeTimes.get() % 3) == 0) {
			return ConsumeOrderlyStatus.ROLLBACK;
		} else if ((this.consumeTimes.get() % 4) == 0) {
			return ConsumeOrderlyStatus.COMMIT;
		} else if ((this.consumeTimes.get() % 5) == 0) {
			consumeOrderlyContext.setSuspendCurrentQueueTimeMillis(3000);
			return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
		}
		return ConsumeOrderlyStatus.SUCCESS;
	}
}
