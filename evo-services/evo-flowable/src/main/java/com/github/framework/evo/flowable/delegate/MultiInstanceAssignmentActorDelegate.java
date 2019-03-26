package com.github.framework.evo.flowable.delegate;

import com.github.framework.evo.flowable.bizz.AssignmentActorBizz;
import com.github.framework.evo.flowable.exception.WorkflowException;
import com.github.framework.evo.flowable.model.AssignmentDto;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.impl.el.FixedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-03-26 20:49
 */
@Slf4j
@Component
public class MultiInstanceAssignmentActorDelegate implements JavaDelegate {
	@Autowired
	private AssignmentActorBizz assignmentActorBizz;

	private FixedValue service;
	private FixedValue uri;

	@Override
	public void execute(DelegateExecution delegateExecution) {
		if (service == null || uri == null) {
			throw new WorkflowException("service和uri不能为null");
		}

		AssignmentDto assignmentDto = assignmentActorBizz.callService(delegateExecution, service, uri);
		delegateExecution.setVariable("pv_multiInstanceAssignmentActors", assignmentDto.getActors());
		delegateExecution.setVariable("pv_multiInstanceCompletionCondition", assignmentDto.getCondition());
		delegateExecution.setVariable("pv_pass_count", 0);
		delegateExecution.setVariable("pv_reject_count", 0);
	}
}
