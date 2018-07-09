package com.ritoinfo.framework.evo.sp.sys.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-06-01 13:27
 */
public class UserExistedException extends BizzException {
	public UserExistedException(String message) {
		super(message);
	}
}
