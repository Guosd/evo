package com.github.framework.evo.flowable.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2019-03-25 22:32
 */
public class WorkflowException extends BaseException {
	public WorkflowException(String message) {
		super(message, null, null);
	}

	public WorkflowException(String message, Object data) {
		super(message, data, null);
	}

	public WorkflowException(String message, Throwable throwable) {
		super(message, null, throwable);
	}

	public WorkflowException(String message, Object data, Throwable throwable) {
		super(message, data, throwable);
	}
}
