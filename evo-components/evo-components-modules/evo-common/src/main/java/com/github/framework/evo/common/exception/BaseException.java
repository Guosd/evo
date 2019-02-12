package com.github.framework.evo.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-09 11:32
 */
public class BaseException extends RuntimeException {
	@Getter @Setter protected Object data;

	public BaseException(String message, Object data, Throwable e) {
		super(message, e);

		this.data = data;
	}
}
