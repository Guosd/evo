package com.ritoinfo.framework.evo.common.jwt.model;

import lombok.Builder;
import lombok.Getter;

/**
 * User: Kyll
 * Date: 2018-03-12 09:12
 */
@Builder
public class TokenInfo {
	@Getter private String token;
	@Getter private String refreshToken;
}
