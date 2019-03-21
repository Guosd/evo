package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-26 14:34
 */
public class UserContextNotExistException extends BaseException {
	public UserContextNotExistException() {
		super(null, null, null);
	}

	public UserContextNotExistException(String message) {
		super(message, null, null);
	}

	public UserContextNotExistException(String message, Object data) {
		super(message, data, null);
	}

	public UserContextNotExistException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public UserContextNotExistException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
