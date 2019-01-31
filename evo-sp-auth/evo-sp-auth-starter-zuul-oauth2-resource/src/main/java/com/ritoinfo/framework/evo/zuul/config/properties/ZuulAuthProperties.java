package com.ritoinfo.framework.evo.zuul.config.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-12-24 14:56
 */
@Data
@Component
@ConfigurationProperties(prefix = "evo.zuul")
public class ZuulAuthProperties {
	@Getter private Auth auth = new Auth();

	@Data
	public static class Auth {
		@Getter private Client client = new Client();

		@Data
		public static class Client {
			private String id;
			private String secret;
		}
	}
}
