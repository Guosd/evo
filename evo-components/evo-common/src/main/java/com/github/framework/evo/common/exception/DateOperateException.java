package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class DateOperateException extends BaseException {
	public DateOperateException(String message) {
		super(message, null, null);
	}

	public DateOperateException(String message, Object data) {
		super(message, data, null);
	}

	public DateOperateException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public DateOperateException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
