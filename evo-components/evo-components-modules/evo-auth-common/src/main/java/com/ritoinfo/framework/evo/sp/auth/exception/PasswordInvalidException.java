package com.ritoinfo.framework.evo.sp.auth.exception;

import com.ritoinfo.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2019-01-04 13:57
 */
public class PasswordInvalidException extends BizzException {
	public PasswordInvalidException(String message) {
		super(message);
	}
}
