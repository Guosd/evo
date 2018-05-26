package com.ritoinfo.framework.evo.sp.activiti.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-02 14:51
 */
public class TaskNotFoundException extends BizzException {
	public TaskNotFoundException(String message) {
		super(message);
	}
}
