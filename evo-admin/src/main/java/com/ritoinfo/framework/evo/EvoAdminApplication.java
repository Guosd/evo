package com.ritoinfo.framework.evo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * User: Kyll
 * Date: 2018-02-26 20:53
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class EvoAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoAdminApplication.class, args);
	}
}
