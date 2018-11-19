package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User: Kyll
 * Date: 2018-07-16 17:13
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpDemoHibernateApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpDemoHibernateApplication.class, args);
	}
}
