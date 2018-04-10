package com.ritoinfo.framework.evo.activiti.exception;

import com.ritoinfo.framework.evo.sp.base.exception.BizzException;

/**
 * User: Kyll
 * Date: 2018-04-10 11:20
 */
public class HistoricTaskInstanceNotFoundException extends BizzException {
	public HistoricTaskInstanceNotFoundException(String message) {
		super(message);
	}
}
