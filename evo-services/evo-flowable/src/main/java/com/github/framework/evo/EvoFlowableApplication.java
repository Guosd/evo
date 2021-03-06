package com.github.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User: Kyll
 * Date: 2017-11-27 11:57
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoFlowableApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoFlowableApplication.class, args);
	}
}
