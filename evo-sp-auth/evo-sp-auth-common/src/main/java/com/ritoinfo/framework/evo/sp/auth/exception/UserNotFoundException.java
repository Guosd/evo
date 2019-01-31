package com.ritoinfo.framework.evo.sp.auth.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-03-09 11:35
 */
public class UserNotFoundException extends BizzException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
