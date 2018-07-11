package com.ritoinfo.framework.evo.sp.auth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-06-06 09:57
 */
@Data
@Configuration
public class AuthConfig {
	@Value("${auth.verifyCode.length}")
	private Integer length;
	@Value("${auth.verifyCode.expirationTime}")
	private Integer expirationTime;
}
