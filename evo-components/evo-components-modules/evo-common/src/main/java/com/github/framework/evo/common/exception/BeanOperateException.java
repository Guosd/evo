package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class BeanOperateException extends BaseException {
	public BeanOperateException(String message) {
		this(message, null);
	}

	public BeanOperateException(String message, Throwable e) {
		this(message, null, e);
	}

	public BeanOperateException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
