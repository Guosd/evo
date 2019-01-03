package com.ritoinfo.framework.evo.zuul.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-12-28 11:35
 */
@Data
@Component
@ConfigurationProperties(prefix = "evo.sp.auth.path")
public class PathProperties {
	private String[] excludes;
}
