package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class NetOperateException extends BaseException {
	public NetOperateException(String message) {
		super(message, null, null);
	}

	public NetOperateException(String message, Object data) {
		super(message, data, null);
	}

	public NetOperateException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public NetOperateException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
