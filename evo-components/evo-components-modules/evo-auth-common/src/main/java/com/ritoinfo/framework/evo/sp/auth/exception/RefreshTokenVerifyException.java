package com.ritoinfo.framework.evo.sp.auth.exception;

import com.ritoinfo.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-17 13:32
 */
public class RefreshTokenVerifyException extends BizzException {
	public RefreshTokenVerifyException(String message) {
		super(message);
	}
}
