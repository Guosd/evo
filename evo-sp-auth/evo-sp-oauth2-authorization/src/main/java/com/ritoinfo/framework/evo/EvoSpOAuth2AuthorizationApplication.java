package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User: Kyll
 * Date: 2017-11-27 11:57
 */
@EnableFeignClients(basePackages = "com.ritoinfo.framework.evo")
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpOAuth2AuthorizationApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpOAuth2AuthorizationApplication.class, args);
	}
}
