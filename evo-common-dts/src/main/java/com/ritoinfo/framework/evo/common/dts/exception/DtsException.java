package com.ritoinfo.framework.evo.common.dts.exception;

import com.ritoinfo.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class DtsException extends BaseException {
	public DtsException(String message) {
		this(message, null);
	}

	public DtsException(String message, Throwable e) {
		this(message, null, e);
	}

	public DtsException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
