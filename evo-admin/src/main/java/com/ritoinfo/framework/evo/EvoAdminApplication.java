package com.ritoinfo.framework.evo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-02-26 20:53
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class EvoAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoAdminApplication.class, args);
	}
}
