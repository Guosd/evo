package com.github.framework.evo.auth.exception;

import com.github.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2019-01-04 13:57
 */
public class PasswordInvalidException extends BizzException {
	public PasswordInvalidException(String message) {
		super(message);
	}
}
