package com.ritoinfo.framework.evo.common.password.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@Configuration
@ConfigurationProperties(prefix = "password")
public class PasswordConfig {
	@Getter @Setter private Integer salt;
}
