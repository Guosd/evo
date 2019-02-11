package com.ritoinfo.framework.evo.autoconfigure.data.redis;

import com.ritoinfo.framework.evo.autoconfigure.application.ApplicationProperties;
import com.ritoinfo.framework.evo.data.redis.RedisKeyGenerator;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2019-02-09 18:57
 */
@ConditionalOnClass(RedisService.class)
@EnableConfigurationProperties({RedisProperties.class})
@Configuration
public class RedisKeyConfiguration {
	@Bean
	public RedisKeyGenerator redisKeyGenerator(ApplicationProperties applicationProperties, RedisProperties redisProperties) {
		return new RedisKeyGenerator(applicationProperties.getApplicationName(), redisProperties.getCompanyPrefix());
	}
}
