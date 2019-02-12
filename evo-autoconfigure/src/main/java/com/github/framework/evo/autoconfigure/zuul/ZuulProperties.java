package com.github.framework.evo.autoconfigure.zuul;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2018-12-24 14:56
 */
@Data
@ConfigurationProperties(prefix = "evo.zuul")
public class ZuulProperties {
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
