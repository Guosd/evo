package com.ritoinfo.framework.evo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * User: Kyll
 * Date: 2018-02-26 20:53
 */
/*@EnableZipkinServer*/
@SpringBootApplication
public class EvoZipkinApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvoZipkinApplication.class, args);
	}
}
