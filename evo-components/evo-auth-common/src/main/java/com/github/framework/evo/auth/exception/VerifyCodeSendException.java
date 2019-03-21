package com.github.framework.evo.auth.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-06-06 09:15
 */
public class VerifyCodeSendException extends BaseException {
	public VerifyCodeSendException(String message) {
		super(message);
	}

	public VerifyCodeSendException(String message, Throwable e) {
		super(message, e);
	}
}
