package com.ritoinfo.framework.evo.sp.auth.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-20 16:36
 */
public class VerifyCodeInvalidException extends BizzException {
	public VerifyCodeInvalidException(String message) {
		super(message);
	}
}