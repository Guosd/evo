package com.github.framework.evo.auth.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-02-09 20:11
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDetailsConfig {
	private String usernameUri;
	private String mobileNumberUri;
	private String updateLoginInfoUri;
}
