package com.ritoinfo.framework.evo.sp.base.exception;

/**
 * User: Kyll
 * Date: 2018-03-05 21:47
 *
 * 业务异常
 * BaseBizz的子类必须抛出指定类型的异常，即此类的子类
 */
public class BizzException extends BaseException {
	public BizzException(String message) {
		this(message, null);
	}

	public BizzException(String message, Throwable e) {
		this(message, null, e);
	}

	public BizzException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
