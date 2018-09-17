package com.ritoinfo.framework.evo;

import com.ritoinfo.framework.evo.bizz.BroadcastConsumerMessageBizz;
import com.ritoinfo.framework.evo.bizz.ConsumerMessageBizz;
import com.ritoinfo.framework.evo.bizz.OrderConsumerMessageBizz;
import com.ritoinfo.framework.evo.bizz.ScheduleConsumerMessageBizz;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * User: Kyll
 * Date: 2018-09-04 13:18
 */
@MapperScan(basePackages = "com.ritoinfo.framework.evo")
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpDemoRocketMQConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpDemoRocketMQConsumerApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final ConsumerMessageBizz consumerMessageBizz,
	                              final OrderConsumerMessageBizz orderConsumerMessageBizz,
	                              final BroadcastConsumerMessageBizz broadcastConsumerMessageBizz,
	                              final ScheduleConsumerMessageBizz scheduleConsumerMessageBizz) {
		return strings -> {
		//	consumerMessageBizz.receive();
		//	orderConsumerMessageBizz.receive();
		//	broadcastConsumerMessageBizz.receive();
			scheduleConsumerMessageBizz.receive();
		};

	}
}
