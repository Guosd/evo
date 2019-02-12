package com.github.framework.evo.auth.exception;

import com.github.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-17 13:32
 */
public class RefreshTokenVerifyException extends BizzException {
	public RefreshTokenVerifyException(String message) {
		super(message);
	}
}
