package com.github.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * User: Kyll
 * Date: 2018-02-26 20:53
 */
@MapperScan(basePackages = "com.github.framework.evo.zuul.dynamicroute.dao")
@EnableFeignClients
@EnableCircuitBreaker
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class EvoZuulFilterApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoZuulFilterApplication.class, args);
	}
}
