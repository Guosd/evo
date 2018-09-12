package com.ritoinfo.framework.evo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.UnsupportedEncodingException;

/**
 * User: Kyll
 * Date: 2018-09-04 13:18
 */
@MapperScan(basePackages = "com.ritoinfo.framework.evo")
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpDemoRocketMQApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpDemoRocketMQApplication.class, args);

		DefaultMQProducer producer = new DefaultMQProducer("test_group");
		producer.setNamesrvAddr("192.168.204.129:9876");
	//	producer.setSendMsgTimeout(10000);
		producer.setVipChannelEnabled(false);

		try {
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}

		for (int i = 0; i < 99; i++) {
			Message msg = null;
			try {
				msg = new Message(
						"TopicTest" /* Topic */,
						"TagA" /* Tag */,
						("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
				);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (msg != null) {
				System.out.println("send " + i);

				SendResult sendResult = null;
				try {
					sendResult = producer.send(msg);
				} catch (MQClientException | RemotingException | InterruptedException | MQBrokerException e) {
					e.printStackTrace();
				}
				System.out.printf("%s%n", sendResult);
			}
		}

		producer.shutdown();
	}
}
