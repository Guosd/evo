package com.ritoinfo.framework.evo.data.redis;

/**
 * User: Kyll
 * Date: 2018-04-16 09:04
 */
public class RedisKeyGenerator {
	private String applicationName;
	private String companyPrefix;

	public RedisKeyGenerator(String applicationName, String companyPrefix) {
		this.applicationName = applicationName;
		this.companyPrefix = companyPrefix;
	}


	public String generate(Object object, String bizzFlag, String key) {
		return generate(object.getClass(), bizzFlag, key);
	}

	public String generate(Class clazz, String bizzFlag, String key) {
		return generate(applicationName, clazz, bizzFlag, key);
	}

	public String generate(String springApplicationName, Class clazz, String bizzFlag, String key) {
		return generate(springApplicationName, clazz.getName(), bizzFlag, key);
	}

	public String generate(String springApplicationName, String clazzName, String bizzFlag, String key) {
		return companyPrefix + ":" + springApplicationName + ":" + clazzName + ":" + bizzFlag + ":" + key;
	}
}
