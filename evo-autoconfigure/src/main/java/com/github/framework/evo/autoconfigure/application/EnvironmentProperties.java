package com.github.framework.evo.autoconfigure.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2019-03-19 09:23
 */
@Data
@ConfigurationProperties("evo.environment")
public class EnvironmentProperties {
	private Exception exception = new Exception();

	@Data
	public static class Exception {
		/**
		 * 是否在HTTP REST响应中显示异常栈信息。默认true
		 */
		private boolean enabled = true;
	}
}
