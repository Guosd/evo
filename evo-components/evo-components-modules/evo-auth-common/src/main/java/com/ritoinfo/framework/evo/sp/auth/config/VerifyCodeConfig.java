package com.ritoinfo.framework.evo.sp.auth.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-02-09 20:09
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VerifyCodeConfig {
	private Integer expirationTime;
	private String type;
	private Integer length;
	private String value;
}
