package com.github.framework.evo.data.redis.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class RedisOperateException extends BaseException {
	public RedisOperateException(String message) {
		super(message, null, null);
	}

	public RedisOperateException(String message, Object data) {
		super(message, data, null);
	}

	public RedisOperateException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public RedisOperateException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
