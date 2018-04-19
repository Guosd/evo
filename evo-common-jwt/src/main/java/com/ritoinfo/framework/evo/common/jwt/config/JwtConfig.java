package com.ritoinfo.framework.evo.common.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2017-12-07 14:30
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
	private String algorithm;
	private String issuer;
	private String signingKey;
	private Integer expirationTime;
	private Integer refreshExpirationTime;
}
