package com.github.framework.evo.zuul.resource.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * User: Kyll
 * Date: 2018-12-21 16:24
 */
public class LoginUserNotExtractedException extends AuthenticationException {
	public LoginUserNotExtractedException(String msg) {
		super(msg);
	}

	public LoginUserNotExtractedException(String msg, Throwable t) {
		super(msg, t);
	}
}
