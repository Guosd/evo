package com.github.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * User: Kyll
 * Date: 2017-11-27 11:57
 */
@EnableSwagger2
@EnableFeignClients(basePackages = "com.github.framework.evo")
@EnableCircuitBreaker
@SpringBootApplication
public class EvoControllerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoControllerApplication.class, args);
	}
}
