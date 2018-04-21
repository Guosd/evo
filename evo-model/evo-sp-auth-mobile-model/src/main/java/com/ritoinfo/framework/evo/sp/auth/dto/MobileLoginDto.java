package com.ritoinfo.framework.evo.sp.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-04-20 13:21
 */
@Data
public class MobileLoginDto {
	@NotBlank
	private String mobileNumber;
	@NotBlank
	private String imei;
	@NotBlank
	private String verifyCode;
}
