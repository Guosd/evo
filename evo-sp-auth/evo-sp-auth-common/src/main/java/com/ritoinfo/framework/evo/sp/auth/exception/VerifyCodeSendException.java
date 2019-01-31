package com.ritoinfo.framework.evo.sp.auth.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-06-06 09:15
 */
public class VerifyCodeSendException extends BizzException {
	public VerifyCodeSendException(String message) {
		super(message);
	}

	public VerifyCodeSendException(String message, Throwable e) {
		super(message, e);
	}
}
