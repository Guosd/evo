package com.ritoinfo.framework.evo.sp.base.starter.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-07-12 15:49
 */
public class OperationNotSupportedException extends BizzException {
	public OperationNotSupportedException() {
		this(null);
	}

	public OperationNotSupportedException(String message) {
		super(message);
	}

	public OperationNotSupportedException(String message, Throwable e) {
		super(message, e);
	}

	public OperationNotSupportedException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
