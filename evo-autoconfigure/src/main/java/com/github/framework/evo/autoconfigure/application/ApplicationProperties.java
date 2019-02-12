package com.github.framework.evo.autoconfigure.application;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-10-11 15:30
 */
@Data
@Component
public class ApplicationProperties {
	@Value("${spring.application.name}")
	private String applicationName;
}
