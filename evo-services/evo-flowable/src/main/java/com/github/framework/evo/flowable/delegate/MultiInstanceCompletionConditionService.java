package com.github.framework.evo.flowable.delegate;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.framework.evo.common.uitl.JsonUtil;
import com.github.framework.evo.flowable.exception.WorkflowException;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-03-26 22:13
 */
@Component
public class MultiInstanceCompletionConditionService {
	public boolean calculate(DelegateExecution delegateExecution) {
		String condition = (String) delegateExecution.getVariable("pv_multiInstanceCompletionCondition");
		JsonNode jsonNode = JsonUtil.jsonToNode(condition);

		boolean result;
		String type = jsonNode.get("type").asText();
		if ("passCount".equals(type)) {
			int count = jsonNode.get("weight").asInt();
			if (count > 0) {
				result = count <= (Integer) delegateExecution.getVariable("pv_pass_count");
			} else {
				result = Math.abs(count) <= (Integer) delegateExecution.getVariable("pv_reject_count");
			}
		} else if ("passPercent".equals(type)) {
			int nrOfInstances = (Integer) delegateExecution.getVariable("nrOfInstances");
			double percent = jsonNode.get("weight").asDouble();
			if (percent > 0) {
				result = percent <= (Integer) delegateExecution.getVariable("pv_pass_count") / (1.0 * nrOfInstances);
			} else {
				result = Math.abs(percent) <= (Integer) delegateExecution.getVariable("pv_reject_count") / (1.0 * nrOfInstances);
			}
		} else if ("weight".equals(type)) {
			JsonNode weightNode = jsonNode.get("weight");
		// TODO new BigDecimal(weightNode.get("pass").asText());
			result = false;
		} else {
			throw new WorkflowException("type = " + type + "，必须是[passCount, passPercent, weight]");
		}

		return result;
	}
}
