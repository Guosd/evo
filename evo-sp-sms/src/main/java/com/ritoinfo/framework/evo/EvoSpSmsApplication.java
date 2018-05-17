package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * User: Kyll
 * Date: 2018-05-17 15:21
 */
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpSmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpSmsApplication.class, args);
	}
}
