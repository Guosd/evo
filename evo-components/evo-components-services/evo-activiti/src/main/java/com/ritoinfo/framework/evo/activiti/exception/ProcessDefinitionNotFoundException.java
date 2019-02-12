package com.ritoinfo.framework.evo.activiti.exception;

import com.ritoinfo.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-02 11:00
 */
public class ProcessDefinitionNotFoundException extends BizzException {
	public ProcessDefinitionNotFoundException(String message) {
		super(message);
	}
}
