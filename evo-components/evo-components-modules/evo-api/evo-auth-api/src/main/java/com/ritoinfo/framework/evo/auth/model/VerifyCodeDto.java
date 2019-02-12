package com.ritoinfo.framework.evo.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2018-05-17 15:02
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VerifyCodeDto {
	private String verifyCode;
	private boolean existUser;
}
