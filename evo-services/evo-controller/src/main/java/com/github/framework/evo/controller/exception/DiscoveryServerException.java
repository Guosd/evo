package com.github.framework.evo.controller.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2019-04-15 17:20
 */
public class DiscoveryServerException extends BaseException {
	public DiscoveryServerException(String message) {
		super(message, null, null);
	}

	public DiscoveryServerException(String message, Object data) {
		super(message, data, null);
	}

	public DiscoveryServerException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public DiscoveryServerException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
