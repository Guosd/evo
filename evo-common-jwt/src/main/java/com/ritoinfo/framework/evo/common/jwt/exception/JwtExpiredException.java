package com.ritoinfo.framework.evo.common.jwt.exception;

/**
 * User: Kyll
 * Date: 2018-04-17 08:45
 */
public class JwtExpiredException extends Exception {
	public JwtExpiredException(String message, Throwable e) {
		super(message, e);
	}
}
