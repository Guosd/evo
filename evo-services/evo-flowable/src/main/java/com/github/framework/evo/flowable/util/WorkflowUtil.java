package com.github.framework.evo.flowable.util;

import org.flowable.common.engine.api.variable.VariableContainer;
import org.flowable.engine.impl.el.FixedValue;

/**
 * User: Kyll
 * Date: 2019-03-25 22:34
 */
public class WorkflowUtil {
	public static String toRestAddress(VariableContainer variableContainer, FixedValue service, FixedValue uri) {
		return "http://" + service.getValue(variableContainer).toString() + uri.getValue(variableContainer).toString();
	}
}
