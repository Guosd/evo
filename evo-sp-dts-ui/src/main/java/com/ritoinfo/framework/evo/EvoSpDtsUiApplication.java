package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User: Kyll
 * Date: 2018-02-26 20:53
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpDtsUiApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpDtsUiApplication.class, args);
	}
}
