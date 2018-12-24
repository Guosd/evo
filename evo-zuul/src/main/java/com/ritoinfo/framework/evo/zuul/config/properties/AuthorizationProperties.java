package com.ritoinfo.framework.evo.zuul.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-12-24 14:56
 */
@Data
@Component
@ConfigurationProperties(prefix = "evo.sp.auth")
public class AuthorizationProperties {
	private String jwtSigningKey;
	private String serviceId;
}
