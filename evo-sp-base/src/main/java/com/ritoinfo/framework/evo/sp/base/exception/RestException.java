package com.ritoinfo.framework.evo.sp.base.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-05 21:59
 *
 * 调用异常
 * 业务异常不要直接抛出，必须转换为此异常的子类
 */
public class RestException extends BaseException {
	@Getter @Setter protected String code;

	public RestException(String code, String message) {
		this(code, message, null);
	}

	public RestException(String code, String message, Object data) {
		this(code, message, data, null);
	}

	public RestException(String code, String message, Object data, Throwable e) {
		super(message, data, e);

		this.code = code;
	}
}
