package com.ritoinfo.framework.evo.bizz;

import com.ritoinfo.framework.evo.mq.rocketmq.assist.MessageHelper;
import com.ritoinfo.framework.evo.mq.rocketmq.producer.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * User: Kyll
 * Date: 2018-09-13 18:07
 */
@Slf4j
@Service
public class OrderMessageBizz {
	private static int count = 0;

	@Autowired
	private RocketMQProducer producer;

	public String send() {
		String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
		Message message = MessageHelper.createMessage("TopicTest", tags[new Random().nextInt(tags.length)], MessageHelper.generateMessageKey(), "Hello RocketMQ Order " + count);
		log.info("order send " + message);

		SendResult sendResult = producer.send(message, (list, message1, o) -> {
			log.info("消息队列选择器 " + list + ", " + message1 + ", " + o);

			Integer id = (Integer) o;
			int index = id % list.size();
			return list.get(index);
		}, count++ % 10);
		log.info("order result " + sendResult);

		return null;
	}
}
