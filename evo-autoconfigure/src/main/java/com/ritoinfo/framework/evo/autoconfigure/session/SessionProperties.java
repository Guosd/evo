package com.ritoinfo.framework.evo.autoconfigure.session;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2019-02-09 11:13
 */
@Data
@ConfigurationProperties(prefix = "evo.session")
public class SessionProperties {
	@Getter private UserContext userContext = new UserContext();

	@Data
	public static class UserContext {
		/**
		 * 通过SessionHolder.getUserContext()获取当前用户。默认true。
		 */
		private boolean enabled = true;
	}

}
