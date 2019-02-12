package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-26 14:34
 */
public class UserContextNotExistException extends BizzException {
	public UserContextNotExistException() {
		this(null);
	}

	public UserContextNotExistException(String message) {
		super(message);
	}
}
