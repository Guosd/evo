package com.github.framework.evo.flowable.listener;

import com.github.framework.evo.flowable.bizz.RepositoryBizz;
import com.github.framework.evo.flowable.bizz.RuntimeBizz;
import com.github.framework.evo.flowable.exception.WorkflowException;
import com.github.framework.evo.flowable.model.AssignmentDto;
import com.github.framework.evo.flowable.model.AssignmentReq;
import com.github.framework.evo.flowable.util.WorkflowUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.engine.impl.el.FixedValue;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kyll
 * Date: 2019-03-24 13:46
 */
@Slf4j
@Component
public class AssignmentListener implements TaskListener {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RepositoryBizz repositoryBizz;
	@Autowired
	private RuntimeBizz runtimeBizz;

	private FixedValue service;
	private FixedValue uri;

	private static HttpHeaders headers = new HttpHeaders();

	static {
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		if (service == null || uri == null) {
			throw new WorkflowException("service和uri不能为null");
		}

		ProcessDefinition processDefinition = repositoryBizz.getProcessDefinitionByIdForRaw(delegateTask.getProcessDefinitionId());
		ProcessInstance processInstance = runtimeBizz.getProcessInstanceByIdForRaw(delegateTask.getProcessInstanceId());
		String restAddress = WorkflowUtil.toRestAddress(delegateTask, service, uri);

		log.info("processDefinitionKey: {}, businessKey: {}, taskDefinitionKey: {}, restAddress: {}", processDefinition.getKey(), processInstance.getBusinessKey(), delegateTask.getTaskDefinitionKey(), restAddress);

		AssignmentDto assignmentDto = restTemplate.postForObject(restAddress, new HttpEntity<>(AssignmentReq.builder()
				.processDefinitionKey(processDefinition.getKey())
				.businessKey(processInstance.getBusinessKey())
				.taskDefinitionKey(delegateTask.getTaskDefinitionKey())
				.variables(delegateTask.getVariables())
				.build(), headers), AssignmentDto.class);
		log.info("AssignmentDto: {}", assignmentDto);

		delegateTask.addCandidateUsers(assignmentDto.getActors());
	}
}
