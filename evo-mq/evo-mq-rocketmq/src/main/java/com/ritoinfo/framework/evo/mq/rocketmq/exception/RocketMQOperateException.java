package com.ritoinfo.framework.evo.mq.rocketmq.exception;

import com.ritoinfo.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-04-12 14:05
 */
public class RocketMQOperateException extends BaseException {
	public RocketMQOperateException(String message) {
		this(message, null);
	}

	public RocketMQOperateException(String message, Throwable e) {
		this(message, null, e);
	}

	public RocketMQOperateException(String message, Object data, Throwable e) {
		super(message, data, e);
	}
}
