package com.github.framework.evo.autoconfigure.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2018-10-11 15:30
 */
@Data
@ConfigurationProperties("spring")
public class SpringProperties {
	private Application application = new Application();

	@Data
	public static class Application {
		private String name;
	}
}
