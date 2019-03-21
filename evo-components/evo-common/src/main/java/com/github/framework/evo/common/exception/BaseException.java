package com.github.framework.evo.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-09 11:32
 */
public abstract class BaseException extends RuntimeException {
	@Getter @Setter protected Object data;

	public BaseException(String message) {
		this(message, null, null);
	}

	public BaseException(String message, Object data) {
		this(message, data, null);
	}

	public BaseException(String message, Throwable throwable) {
		this(message, null, throwable);
	}

	public BaseException(String message, Object data, Throwable throwable) {
		super(message, throwable);

		this.data = data;
	}
}
