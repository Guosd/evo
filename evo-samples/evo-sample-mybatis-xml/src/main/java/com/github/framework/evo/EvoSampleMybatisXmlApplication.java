package com.github.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User: Kyll
 * Date: 2018-07-12 11:21
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSampleMybatisXmlApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSampleMybatisXmlApplication.class, args);
	}
}
