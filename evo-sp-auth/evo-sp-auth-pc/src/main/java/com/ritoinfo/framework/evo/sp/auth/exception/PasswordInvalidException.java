package com.ritoinfo.framework.evo.sp.auth.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-03-12 09:05
 */
public class PasswordInvalidException extends BizzException {
	public PasswordInvalidException(String message) {
		super(message);
	}
}
