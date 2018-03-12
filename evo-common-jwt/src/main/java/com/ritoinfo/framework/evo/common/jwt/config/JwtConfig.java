package com.ritoinfo.framework.evo.common.jwt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2017-12-07 14:30
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
	@Getter @Setter private String algorithm;
	@Getter @Setter private String issuer;
	@Getter @Setter private String signingKey;
	@Getter @Setter private Integer expirationTime;
	@Getter @Setter private Integer refreshExpirationTime;
	@Getter @Setter private String header;
	@Getter @Setter private String headPrefix;
}
