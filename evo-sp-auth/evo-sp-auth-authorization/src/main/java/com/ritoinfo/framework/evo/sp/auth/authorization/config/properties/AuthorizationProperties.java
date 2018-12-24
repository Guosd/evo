package com.ritoinfo.framework.evo.sp.auth.authorization.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-12-21 10:19
 */
@Data
@Component
@ConfigurationProperties(prefix = "evo.sp.auth")
public class AuthorizationProperties {
	private String jwtSigningKey;
	private String userDetailsMode;
	private String userDetailsServiceId;
	private String userDetailsPasswordUri;
	private String userDetailsMnvcUri;
}
