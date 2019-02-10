package com.ritoinfo.framework.evo.sp.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-04-20 16:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SmsDto {
	@NotBlank
	private String phoneNo;
	@NotBlank
	private String content;
}
