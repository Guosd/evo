package com.github.framework.evo.activiti.exception;

import com.github.framework.evo.common.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-10 11:20
 */
public class HistoricTaskInstanceNotFoundException extends BizzException {
	public HistoricTaskInstanceNotFoundException(String message) {
		super(message);
	}
}
