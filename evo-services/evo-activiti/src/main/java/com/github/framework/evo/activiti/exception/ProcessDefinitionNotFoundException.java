package com.github.framework.evo.activiti.exception;

import com.github.framework.evo.common.exception.BaseException;

/**
 * User: Kyll
 * Date: 2018-04-02 11:00
 */
public class ProcessDefinitionNotFoundException extends BaseException {
	public ProcessDefinitionNotFoundException(String message) {
		super(message);
	}
}
