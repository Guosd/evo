package com.ritoinfo.framework.evo.sp.auth.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-17 13:39
 */
public class AccessTokenInvalidException extends BizzException {
	public AccessTokenInvalidException(String message) {
		super(message);
	}
}
