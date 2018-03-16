package com.ritoinfo.framework.evo.zuul.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2017-12-05 22:14
 */
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {
	@Getter @Setter private String loginPath;
	@Getter @Setter private String logoutPath;
	@Getter @Setter private String[] excludePaths;
}
