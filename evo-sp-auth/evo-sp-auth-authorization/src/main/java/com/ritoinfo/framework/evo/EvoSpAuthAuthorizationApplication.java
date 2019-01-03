package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * User: Kyll
 * Date: 2017-11-27 11:57
 */
@MapperScan(basePackages = "com.ritoinfo.framework.evo.sp.auth.authorization.dao")
@EnableFeignClients(basePackages = "com.ritoinfo.framework.evo.sp.auth.authorization.api")
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpAuthAuthorizationApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoSpAuthAuthorizationApplication.class, args);
	}
}
