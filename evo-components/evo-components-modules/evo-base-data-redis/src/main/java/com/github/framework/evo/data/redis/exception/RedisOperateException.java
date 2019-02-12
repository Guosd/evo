package com.github.framework.evo.data.redis.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class RedisOperateException extends BaseException {
	public RedisOperateException(String message) {
		this(message, null);
	}

	public RedisOperateException(String message, Throwable e) {
		this(message, null, e);
	}

	public RedisOperateException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
