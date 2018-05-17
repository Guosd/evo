package com.ritoinfo.framework.evo.sp.auth.dto;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-05-17 15:02
 */
@Data
public class CodeDto {
	private String verifyCode;
	private boolean existUser;
}
