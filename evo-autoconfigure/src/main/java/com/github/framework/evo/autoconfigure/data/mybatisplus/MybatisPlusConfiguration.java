package com.github.framework.evo.autoconfigure.data.mybatisplus;

import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2019-03-29 18:35
 */
@ConditionalOnClass(MybatisMapWrapperFactory.class)
@Configuration
public class MybatisPlusConfiguration {
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
