package com.github.framework.evo.auth.exception;

import com.github.framework.evo.common.exception.BusinessException;

/**
 * User: Kyll
 * Date: 2018-03-09 11:35
 */
public class UserNotFoundException extends BusinessException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
