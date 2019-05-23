package com.github.framework.evo.autoconfigure.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kyll
 * Date: 2019-05-23 10:27
 */
@Configuration
@EnableConfigurationProperties(EurekaProperties.class)
public class EurekaConfiguration {
	private final EurekaProperties eurekaProperties;

	@Autowired
	public EurekaConfiguration(EurekaProperties eurekaProperties) {
		this.eurekaProperties = eurekaProperties;
	}

	@ConditionalOnMissingBean(RestTemplate.class)
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
