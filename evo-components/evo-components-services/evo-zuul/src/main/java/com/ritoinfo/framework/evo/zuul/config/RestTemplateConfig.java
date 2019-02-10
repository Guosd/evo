package com.ritoinfo.framework.evo.zuul.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kyll
 * Date: 2019-01-03 13:19
 */
@Configuration
public class RestTemplateConfig {
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
