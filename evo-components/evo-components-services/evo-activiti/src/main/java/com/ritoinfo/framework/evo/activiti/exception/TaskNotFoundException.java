package com.ritoinfo.framework.evo.activiti.exception;

import com.ritoinfo.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-02 14:51
 */
public class TaskNotFoundException extends BizzException {
	public TaskNotFoundException(String message) {
		super(message);
	}
}
