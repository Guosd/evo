package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class JsonOperateException extends BaseException {
	public JsonOperateException(String message) {
		this(message, null);
	}

	public JsonOperateException(String message, Throwable e) {
		this(message, null, e);
	}

	public JsonOperateException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
