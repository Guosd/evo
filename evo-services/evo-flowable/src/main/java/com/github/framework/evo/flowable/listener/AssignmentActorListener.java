package com.github.framework.evo.flowable.listener;

import com.github.framework.evo.flowable.bizz.AssignmentActorBizz;
import com.github.framework.evo.flowable.exception.WorkflowException;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.engine.impl.el.FixedValue;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-03-24 13:46
 */
@Slf4j
@Component
public class AssignmentActorListener implements TaskListener {
	@Autowired
	private AssignmentActorBizz assignmentActorBizz;

	private FixedValue service;
	private FixedValue uri;

	@Override
	public void notify(DelegateTask delegateTask) {
		if (service == null || uri == null) {
			throw new WorkflowException("service和uri不能为null");
		}

		delegateTask.addCandidateUsers(assignmentActorBizz.callService(delegateTask, service, uri).getActors());
	}
}
