package com.ritoinfo.framework.evo.common.password.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-12-20 12:09
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "password")
public class PasswordProperties {
	private Integer salt;
}
