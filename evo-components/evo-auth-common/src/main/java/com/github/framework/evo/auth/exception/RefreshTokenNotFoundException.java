package com.github.framework.evo.auth.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-04-17 13:39
 */
public class RefreshTokenNotFoundException extends BaseException {
	public RefreshTokenNotFoundException(String message) {
		super(message);
	}
}
