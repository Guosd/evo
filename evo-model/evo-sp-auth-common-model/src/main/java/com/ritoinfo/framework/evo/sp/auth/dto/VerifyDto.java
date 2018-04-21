package com.ritoinfo.framework.evo.sp.auth.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-04-15 16:39
 */
@Builder
@Data
public class VerifyDto {
	@NotBlank
	private String uri;
	@NotBlank
	private String token;
}
