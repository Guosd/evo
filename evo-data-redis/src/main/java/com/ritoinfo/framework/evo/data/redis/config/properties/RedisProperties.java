package com.ritoinfo.framework.evo.data.redis.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2019-01-03 10:19
 */
@Data
@Configuration
public class RedisProperties {
	@Value("${redis.companyPrefix}")
	private String redisCompanyPrefix;
}
