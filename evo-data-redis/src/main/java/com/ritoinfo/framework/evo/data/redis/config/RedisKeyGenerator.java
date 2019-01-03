package com.ritoinfo.framework.evo.data.redis.config;

import com.ritoinfo.framework.evo.common.config.properties.ApplicationProperties;
import com.ritoinfo.framework.evo.data.redis.config.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-04-16 09:04
 */
@Component
public class RedisKeyGenerator {
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private RedisProperties redisProperties;

	public String generate(Object object, String bizzFlag, String key) {
		return generate(object.getClass(), bizzFlag, key);
	}

	public String generate(Class clazz, String bizzFlag, String key) {
		return generate(applicationProperties.getApplicationName(), clazz, bizzFlag, key);
	}

	public String generate(String springApplicationName, Class clazz, String bizzFlag, String key) {
		return generate(springApplicationName, clazz.getName(), bizzFlag, key);
	}

	public String generate(String springApplicationName, String clazzName, String bizzFlag, String key) {
		return redisProperties.getRedisCompanyPrefix() + ":" + springApplicationName + ":" + clazzName + ":" + bizzFlag + ":" + key;
	}
}
