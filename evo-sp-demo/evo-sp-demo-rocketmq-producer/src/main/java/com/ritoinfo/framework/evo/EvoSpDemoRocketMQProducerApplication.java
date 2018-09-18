package com.ritoinfo.framework.evo;

import com.ritoinfo.framework.evo.bizz.AsynchronouslyProducerMessageBizz;
import com.ritoinfo.framework.evo.bizz.BroadcastProducerMessageBizz;
import com.ritoinfo.framework.evo.bizz.OnewayProducerMessageBizz;
import com.ritoinfo.framework.evo.bizz.OrderProducerMessageBizz;
import com.ritoinfo.framework.evo.bizz.ScheduleProducerMessageBizz;
import com.ritoinfo.framework.evo.bizz.SynchronouslyProducerMessageBizz;
import com.ritoinfo.framework.evo.bizz.TransactionProducerMessageBizz;
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
public class EvoSpDemoRocketMQProducerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpDemoRocketMQProducerApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final SynchronouslyProducerMessageBizz synchronouslyProducerMessageBizz,
	                              final AsynchronouslyProducerMessageBizz asynchronouslyProducerMessageBizz,
	                              final OnewayProducerMessageBizz onewayProducerMessageBizz,

	                              final OrderProducerMessageBizz orderProducerMessageBizz,

	                              final BroadcastProducerMessageBizz broadcastProducerMessageBizz,

	                              final ScheduleProducerMessageBizz scheduleProducerMessageBizz,

	                              final TransactionProducerMessageBizz transactionProducerMessageBizz) {
		return strings -> {
		//	synchronouslyProducerMessageBizz.send();
			asynchronouslyProducerMessageBizz.send();
		//	onewayProducerMessageBizz.send();
		//	orderProducerMessageBizz.send();
		//	broadcastProducerMessageBizz.send();
		//	scheduleProducerMessageBizz.send();
		//	transactionProducerMessageBizz.send();
		};

	}
}
