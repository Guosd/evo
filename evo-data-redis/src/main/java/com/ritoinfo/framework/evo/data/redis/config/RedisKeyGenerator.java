package com.ritoinfo.framework.evo.data.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-04-16 09:04
 */
@Configuration
public class RedisKeyGenerator {
	private static String springApplicationName;

	@Value("${spring.application.name}")
	public void setSpringApplicationName(String springApplicationName) {
		RedisKeyGenerator.springApplicationName = springApplicationName;
	}

	public static String generate(Object object, String bizzFlag, String key) {
		return generate(object.getClass(), bizzFlag, key);
	}

	public static String generate(Class clazz, String bizzFlag, String key) {
		return springApplicationName + ":" + clazz.getName() + ":" + bizzFlag + ":" + key;
	}
}
