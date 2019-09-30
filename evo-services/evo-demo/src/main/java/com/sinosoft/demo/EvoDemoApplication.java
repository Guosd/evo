package com.sinosoft.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User: Kyll
 * Date: 2017-11-27 11:57
 */
@MapperScan(basePackages={"com.sinosoft.demo.dao"})
@SpringBootApplication
public class EvoDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoDemoApplication.class, args);
	}
}
