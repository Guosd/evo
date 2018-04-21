package com.ritoinfo.framework.evo.sp.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-04-20 16:30
 */
@Data
public class MobileCodeDto {
	@NotBlank
	private String mobileNumber;
	@NotBlank
	private String imei;
}
