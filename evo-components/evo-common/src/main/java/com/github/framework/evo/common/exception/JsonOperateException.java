package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class JsonOperateException extends BaseException {
	public JsonOperateException(String message) {
		super(message, null, null);
	}

	public JsonOperateException(String message, Object data) {
		super(message, data, null);
	}

	public JsonOperateException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public JsonOperateException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
