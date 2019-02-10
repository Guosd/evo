package com.ritoinfo.framework.evo.autoconfigure.application;

import com.ritoinfo.framework.evo.common.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@Configuration
public class ApplicationConfiguration {
	private final ApplicationProperties applicationProperties;

	@Autowired
	public ApplicationConfiguration(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Bean
	public ApplicationConfig applicationConfig() {
		return getApplicationConfig(applicationProperties);
	}


	private ApplicationConfig getApplicationConfig(ApplicationProperties applicationProperties) {
		return ApplicationConfig.builder().applicationName(applicationProperties.getApplicationName()).build();
	}
}
