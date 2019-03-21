package com.github.framework.evo.sys.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-06-01 13:27
 */
public class UserExistedException extends BaseException {
	public UserExistedException(String message) {
		super(message);
	}
}
