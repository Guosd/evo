package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * User: Kyll
 * Date: 2018-02-26 20:53
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class EvoConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoConfigApplication.class, args);
	}
}
