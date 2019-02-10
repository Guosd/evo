package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * User: Kyll
 * Date: 2017-11-27 11:29
 */
@EnableEurekaServer
@SpringBootApplication
public class EvoEurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoEurekaApplication.class, args);
	}
}
