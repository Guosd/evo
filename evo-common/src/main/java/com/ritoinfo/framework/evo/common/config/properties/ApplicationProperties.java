package com.ritoinfo.framework.evo.common.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-10-11 15:30
 */
@Data
@Configuration
public class ApplicationProperties {
	@Value("${spring.application.name}")
	private String applicationName;
}
