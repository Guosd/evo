package com.github.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2019-04-12 09:44
 */
public class CryptoOperateException extends BaseException {
	public CryptoOperateException(String message) {
		super(message, null, null);
	}

	public CryptoOperateException(String message, Object data) {
		super(message, data, null);
	}

	public CryptoOperateException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public CryptoOperateException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
