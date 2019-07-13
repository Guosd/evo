package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User: Guosd
 * Date: 2019-07-13 08：36
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class EvoDeployApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoDeployApplication.class, args);
	}
}
