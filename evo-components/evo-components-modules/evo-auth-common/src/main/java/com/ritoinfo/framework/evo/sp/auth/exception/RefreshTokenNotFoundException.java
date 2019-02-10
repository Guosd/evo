package com.ritoinfo.framework.evo.sp.auth.exception;

import com.ritoinfo.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-17 13:39
 */
public class RefreshTokenNotFoundException extends BizzException {
	public RefreshTokenNotFoundException(String message) {
		super(message);
	}
}
