package com.ritoinfo.framework.evo;

import com.maihaoche.starter.mq.annotation.EnableMQConfiguration;
import com.ritoinfo.framework.evo.bizz.AsynchronouslyProducerMessageBizz;
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
@EnableMQConfiguration
@MapperScan(basePackages = "com.ritoinfo.framework.evo")
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpDemoRocketMQProducerStarterApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpDemoRocketMQProducerStarterApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final AsynchronouslyProducerMessageBizz asynchronouslyProducerMessageBizz,
	                              final TransactionProducerMessageBizz transactionProducerMessageBizz) {
		return strings -> {
			transactionProducerMessageBizz.send();
		};
	}
}
