package com.github.framework.evo.flowable.listener;

import com.github.framework.evo.flowable.exception.WorkflowException;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-03-26 17:09
 */
@Slf4j
@Component
public class TaskStrategyListener implements TaskListener {
	@Override
	public void notify(DelegateTask delegateTask) {
		String outgoing = (String) delegateTask.getVariable("pv_outgoing");

		if ("pass".equals(outgoing)) {
			delegateTask.setVariable("pv_pass_count", ((Integer) delegateTask.getVariable("pv_pass_count")) + 1);
		} else if ("reject".equals(outgoing)) {
			delegateTask.setVariable("pv_reject_count", ((Integer) delegateTask.getVariable("pv_reject_count")) + 1);
		} else {
			throw new WorkflowException("pv_outgoing = " + outgoing + "，必须是[pass, reject]");
		}
	}
}
