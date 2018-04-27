package com.ritoinfo.framework.evo.sp.base.starter.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-26 14:34
 */
public class UserContextNotExistException extends BizzException {
	public UserContextNotExistException() {
		this(null);
	}

	public UserContextNotExistException(String message) {
		super(message);
	}
}
