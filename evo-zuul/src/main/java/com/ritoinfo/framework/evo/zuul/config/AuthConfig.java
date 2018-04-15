package com.ritoinfo.framework.evo.zuul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2017-12-05 22:14
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {
	private String loginPath;
	private String logoutPath;
	private String[] excludePaths;
}
