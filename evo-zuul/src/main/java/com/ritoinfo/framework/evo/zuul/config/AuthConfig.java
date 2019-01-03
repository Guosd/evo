package com.ritoinfo.framework.evo.zuul.config;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2017-12-05 22:14
 */
@Data
/*@Component
@ConfigurationProperties(prefix = "auth")*/
public class AuthConfig {
	private String loginPath;
	private String[] excludePaths;
}
