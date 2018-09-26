package com.ritoinfo.framework.evo;

import com.ritoinfo.framework.evo.bizz.RpcServerMessageBizz;
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
public class EvoSpDemoRabbitMQConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpDemoRabbitMQConsumerApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final RpcServerMessageBizz rpcServerMessageBizz) {
		return strings -> {
			rpcServerMessageBizz.process();
		};
	}
}
