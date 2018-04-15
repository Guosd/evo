package com.ritoinfo.framework.evo.sp.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-03-08 14:38
 */
@Data
public class LoginDto {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
