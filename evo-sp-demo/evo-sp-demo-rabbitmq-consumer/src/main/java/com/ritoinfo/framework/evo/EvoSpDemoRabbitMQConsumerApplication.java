package com.ritoinfo.framework.evo;

import com.ritoinfo.framework.evo.bizz.RecvMessageBizz;
import com.ritoinfo.framework.evo.bizz.Work01MessageBizz;
import com.ritoinfo.framework.evo.bizz.Work02MessageBizz;
import com.ritoinfo.framework.evo.bizz.Work03MessageBizz;
import com.ritoinfo.framework.evo.bizz.Work04MessageBizz;
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
	public CommandLineRunner init(final RecvMessageBizz recvMessageBizz,
	                              final Work01MessageBizz work01MessageBizz,
	                              final Work02MessageBizz work02MessageBizz,
	                              final Work03MessageBizz work03MessageBizz,
	                              final Work04MessageBizz work04MessageBizz) {
		return strings -> {
		//	recvMessageBizz.process();
			work01MessageBizz.process();
			work02MessageBizz.process();
		//	work03MessageBizz.process();
		//	work04MessageBizz.process();
		};
	}
}
