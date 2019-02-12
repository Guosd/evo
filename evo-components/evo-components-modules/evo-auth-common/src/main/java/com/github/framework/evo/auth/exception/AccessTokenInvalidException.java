package com.github.framework.evo.auth.exception;

import com.github.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-17 13:39
 */
public class AccessTokenInvalidException extends BizzException {
	public AccessTokenInvalidException(String message) {
		super(message);
	}
}
