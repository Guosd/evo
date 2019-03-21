package com.github.framework.evo.autoconfigure.application;

import com.github.framework.evo.base.ApplicationEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@Configuration
@EnableConfigurationProperties({SpringProperties.class, EnvironmentProperties.class})
public class ApplicationConfiguration {
	private final SpringProperties springProperties;
	private final EnvironmentProperties environmentProperties;

	@Autowired
	public ApplicationConfiguration(SpringProperties springProperties, EnvironmentProperties environmentProperties) {
		this.springProperties = springProperties;
		this.environmentProperties = environmentProperties;
	}

	@Bean
	public ApplicationEnvironment applicationEnvironment() {
		return getApplicationConfig(springProperties, environmentProperties);
	}

	private ApplicationEnvironment getApplicationConfig(SpringProperties springProperties, EnvironmentProperties environmentProperties) {
		return ApplicationEnvironment.builder()
				.applicationName(springProperties.getApplication().getName())
				.exceptionEnabled(environmentProperties.getException().isEnabled())
				.build();
	}
}
