package com.github.framework.evo.auth.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-02-09 20:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JwtConfig {
	private String issuer;
	private String signingKey;
	private String secretKey;
	private Integer expirationTime;
	private Integer refreshExpirationTime;
	private Integer oldExpirationTime;
}
