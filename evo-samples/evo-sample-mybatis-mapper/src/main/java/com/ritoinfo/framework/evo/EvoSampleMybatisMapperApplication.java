package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * User: Kyll
 * Date: 2018-07-12 11:21
 */
@MapperScan(basePackages = "com.ritoinfo.framework.evo")
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSampleMybatisMapperApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSampleMybatisMapperApplication.class, args);
	}
}
