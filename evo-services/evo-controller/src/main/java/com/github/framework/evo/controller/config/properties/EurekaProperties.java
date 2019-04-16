package com.github.framework.evo.controller.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2019-04-16 08:05
 */
@Data
@ConfigurationProperties(prefix = "evo.eureka")
public class EurekaProperties {
}
