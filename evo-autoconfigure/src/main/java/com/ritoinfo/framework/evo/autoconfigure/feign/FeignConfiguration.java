package com.ritoinfo.framework.evo.autoconfigure.feign;

import com.ritoinfo.framework.evo.base.starter.interceptor.OpenFeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2019-02-09 12:46
 */
@Configuration
public class FeignConfiguration {
	@Bean
	public OpenFeignRequestInterceptor requestInterceptor() {
		return new OpenFeignRequestInterceptor();
	}
}
