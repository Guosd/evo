package com.github.framework.evo.autoconfigure.feign;

import com.github.framework.evo.base.interceptor.OpenFeignRequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2019-02-09 12:46
 */
@ConditionalOnClass(FeignContext.class)
@Configuration
public class FeignConfiguration {
	@Bean
	public OpenFeignRequestInterceptor requestInterceptor() {
		return new OpenFeignRequestInterceptor();
	}
}
