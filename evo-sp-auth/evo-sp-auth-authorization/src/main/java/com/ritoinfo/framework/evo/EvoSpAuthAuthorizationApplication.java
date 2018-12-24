package com.ritoinfo.framework.evo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Bean
	public CommandLineRunner init(final PasswordEncoder passwordEncoder) {

		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				System.out.println(passwordEncoder.encode("123456"));
				System.out.println(passwordEncoder.encode("secret_1"));
			}
		};

	}
}
