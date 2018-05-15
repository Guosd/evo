package com.ritoinfo.framework.evo.sp.auth.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-05-15 16:22
 */
@Builder
@Data
public class DefaultUserDto {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String mobileNumber;
}
