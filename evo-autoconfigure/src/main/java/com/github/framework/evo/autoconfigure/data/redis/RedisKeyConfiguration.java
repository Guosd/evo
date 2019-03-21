package com.github.framework.evo.autoconfigure.data.redis;

import com.github.framework.evo.autoconfigure.application.ApplicationConfiguration;
import com.github.framework.evo.base.ApplicationEnvironment;
import com.github.framework.evo.data.redis.RedisKeyGenerator;
import com.github.framework.evo.data.redis.service.RedisService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2019-02-09 18:57
 */
@Configuration
@AutoConfigureAfter(ApplicationConfiguration.class)
@EnableConfigurationProperties({RedisProperties.class})
@ConditionalOnClass(RedisService.class)
public class RedisKeyConfiguration {
	@Bean
	public RedisKeyGenerator redisKeyGenerator(ApplicationEnvironment applicationEnvironment, RedisProperties redisProperties) {
		return new RedisKeyGenerator(applicationEnvironment.getApplicationName(), redisProperties.getCompanyPrefix());
	}
}
