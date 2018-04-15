package com.ritoinfo.framework.evo.sp.auth.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * User: Kyll
 * Date: 2018-03-12 09:12
 */
@Builder
@Data
public class TokenDto {
	@Getter private String token;
	@Getter private String refreshToken;
}
