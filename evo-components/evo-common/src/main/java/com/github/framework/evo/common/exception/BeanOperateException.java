package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class BeanOperateException extends BaseException {
	public BeanOperateException(String message) {
		super(message, null, null);
	}

	public BeanOperateException(String message, Object data) {
		super(message, data, null);
	}

	public BeanOperateException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public BeanOperateException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
