package com.github.framework.evo.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-04-20 13:21
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MobileNumberParam {
	@NotBlank
	private String mobileNumber;
	private String imei;
	@NotBlank
	private String verifyCode;
}
