package com.ritoinfo.framework.evo.sp.sms.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-06-05 19:58
 */
public class SmsException extends BizzException {
	public SmsException(String message) {
		super(message);
	}

	public SmsException(String message, Throwable e) {
		super(message, e);
	}
}
