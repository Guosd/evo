package com.ritoinfo.framework.evo.common.exception;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class NetOperateException extends BaseException {
	public NetOperateException(String message) {
		this(message, null);
	}

	public NetOperateException(String message, Throwable e) {
		this(message, null, e);
	}

	public NetOperateException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
