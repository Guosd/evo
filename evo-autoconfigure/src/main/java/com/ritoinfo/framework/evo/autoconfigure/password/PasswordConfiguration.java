package com.ritoinfo.framework.evo.autoconfigure.password;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@EnableConfigurationProperties(PasswordProperties.class)
@Configuration
public class PasswordConfiguration {
	@Bean
	public PasswordEncoder passwordEncoder(PasswordProperties passwordProperties) {
		return new BCryptPasswordEncoder(passwordProperties.getStrength());
	}
}
