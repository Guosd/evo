package com.github.framework.evo.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-05 21:47
 *
 * 业务异常
 */
public class BusinessException extends BaseException {
	@Getter @Setter protected String code;

	public BusinessException(String message) {
		this(null, message, null, null);
	}

	public BusinessException(String message, Object data) {
		this(null, message, data, null);
	}

	public BusinessException(String message, Throwable throwable) {
		this(null, message, null, throwable);
	}

	public BusinessException(String code, String message) {
		this(code, message, null, null);
	}

	public BusinessException(String code, String message, Object data) {
		this(code, message, data, null);
	}

	public BusinessException(String code, String message, Throwable throwable) {
		this(code, message, null, throwable);
	}

	public BusinessException(String code, String message, Object data, Throwable throwable) {
		super(message, data, throwable);

		this.code = code;
	}
}
