package com.ritoinfo.framework.evo.autoconfigure.data.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2019-02-09 11:13
 */
@Data
@ConfigurationProperties(prefix = "evo.redis")
public class RedisProperties {
	/**
	 * 公司前缀。默认EVO
	 */
	private String companyPrefix = "EVO";
}
