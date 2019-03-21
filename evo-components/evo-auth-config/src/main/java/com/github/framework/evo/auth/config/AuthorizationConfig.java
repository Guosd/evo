package com.github.framework.evo.auth.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-02-09 20:10
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuthorizationConfig {
	private String serviceId;
}
