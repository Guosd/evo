package com.ritoinfo.framework.evo.sp.activiti.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kyll
 * Date: 2018-06-02 10:16
 */
@Configuration
public class RestTemplateConfig {
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
}
