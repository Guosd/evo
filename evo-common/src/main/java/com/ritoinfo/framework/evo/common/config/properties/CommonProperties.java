package com.ritoinfo.framework.evo.common.config.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-10-11 15:30
 */
@Component
@ConfigurationProperties(prefix = "evo.common")
public class CommonProperties {
	@Getter private Password password = new Password();
	@Getter private Session session = new Session();

	@Data
	public static class Password {
		private Integer salt;
	}

	@Data
	public static class Session {
		private boolean enabled = true;
	}
}
