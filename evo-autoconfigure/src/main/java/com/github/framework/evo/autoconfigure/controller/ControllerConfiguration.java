package com.github.framework.evo.autoconfigure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * User: Kyll
 * Date: 2019-05-23 10:27
 */
@Configuration
@EnableConfigurationProperties(ControllerProperties.class)
public class ControllerConfiguration {
	private final ControllerProperties controllerProperties;

	@Autowired
	public ControllerConfiguration(ControllerProperties controllerProperties) {
		this.controllerProperties = controllerProperties;
	}

	@ConditionalOnMissingBean(RestTemplate.class)
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebMvcConfigurationSupport getConfigurer() {
		return new WebMvcConfigurationSupport() {
			@Override
			protected void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(true)
						.exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(18000L);
			}
		};
	}
}
