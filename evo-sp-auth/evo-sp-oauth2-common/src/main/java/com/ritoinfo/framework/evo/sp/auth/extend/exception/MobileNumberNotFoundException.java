package com.ritoinfo.framework.evo.sp.auth.extend.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * User: Kyll
 * Date: 2018-12-21 16:24
 */
public class MobileNumberNotFoundException extends AuthenticationException {
	public MobileNumberNotFoundException(String msg) {
		super(msg);
	}

	public MobileNumberNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
