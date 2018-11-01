package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * User: Kyll
 * Date: 2018-02-26 20:53
 */
@EnableAsync
@MapperScan(basePackages = "com.ritoinfo.framework.evo.sp.dts")
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpDtsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpDtsApplication.class, args);
	}
}
