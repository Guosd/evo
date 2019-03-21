package com.github.framework.evo.sms.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-06-05 19:58
 */
public class SmsException extends BaseException {
	public SmsException(String message) {
		super(message);
	}

	public SmsException(String message, Throwable e) {
		super(message, e);
	}
}
