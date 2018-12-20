package com.ritoinfo.framework.evo.common.password.config;

import com.ritoinfo.framework.evo.common.password.config.properties.PasswordProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@Configuration
public class PasswordConfig {
	@Autowired
	private PasswordProperties passwordProperties;

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(passwordProperties.getSalt());
	}
}
