package com.ritoinfo.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class DateOperateException extends BaseException {
	public DateOperateException(String message) {
		this(message, null);
	}

	public DateOperateException(String message, Throwable e) {
		this(message, null, e);
	}

	public DateOperateException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
