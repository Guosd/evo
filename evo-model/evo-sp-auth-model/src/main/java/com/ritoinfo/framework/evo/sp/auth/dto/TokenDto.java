package com.ritoinfo.framework.evo.sp.auth.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-03-12 09:12
 */
@Builder
@Data
public class TokenDto {
	@NotBlank
	private String token;
	@NotBlank
	private String refreshToken;
}
