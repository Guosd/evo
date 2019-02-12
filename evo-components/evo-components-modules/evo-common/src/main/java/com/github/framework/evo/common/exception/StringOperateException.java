package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class StringOperateException extends BaseException {
	public StringOperateException(String message) {
		this(message, null);
	}

	public StringOperateException(String message, Throwable e) {
		this(message, null, e);
	}

	public StringOperateException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
