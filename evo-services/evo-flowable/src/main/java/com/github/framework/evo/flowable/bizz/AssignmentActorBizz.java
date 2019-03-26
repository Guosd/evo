package com.github.framework.evo.flowable.bizz;

import com.github.framework.evo.flowable.model.AssignmentDto;
import com.github.framework.evo.flowable.model.AssignmentReq;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.impl.el.FixedValue;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.variable.api.delegate.VariableScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kyll
 * Date: 2019-03-26 21:08
 */
@Slf4j
@Service
public class AssignmentActorBizz {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RepositoryBizz repositoryBizz;
	@Autowired
	private RuntimeBizz runtimeBizz;

	private static HttpHeaders httpHeaders = new HttpHeaders();

	static {
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}

	public AssignmentDto callService(DelegateTask delegateTask, FixedValue service, FixedValue uri) {
		return callService(delegateTask.getProcessDefinitionId(), delegateTask.getProcessInstanceId(), delegateTask.getTaskDefinitionKey(), delegateTask, service, uri);
	}

	public AssignmentDto callService(DelegateExecution delegateExecution, FixedValue service, FixedValue uri) {
		return callService(delegateExecution.getProcessDefinitionId(), delegateExecution.getProcessInstanceId(), delegateExecution.getCurrentActivityId(), delegateExecution, service, uri);
	}

	private AssignmentDto callService(String processDefinitionId, String processInstanceId, String taskDefinitionKey, VariableScope variableScope, FixedValue service, FixedValue uri) {
		ProcessDefinition processDefinition = repositoryBizz.getProcessDefinitionByIdForRaw(processDefinitionId);
		ProcessInstance processInstance = runtimeBizz.getProcessInstanceByIdForRaw(processInstanceId);
		String restAddress = "http://" + service.getValue(variableScope).toString() + uri.getValue(variableScope).toString();
		log.info("ProcessDefinitionKey: {}, BusinessKey: {}, TaskDefinitionKey: {}, RestAddress: {}", processDefinition.getKey(), processInstance.getBusinessKey(), taskDefinitionKey, restAddress);

		AssignmentDto assignmentDto = restTemplate.postForObject(restAddress, new HttpEntity<>(AssignmentReq.builder()
				.processDefinitionKey(processDefinition.getKey())
				.businessKey(processInstance.getBusinessKey())
				.taskDefinitionKey(taskDefinitionKey)
				.variables(variableScope.getVariables())
				.build(), httpHeaders), AssignmentDto.class);
		log.info("AssignmentDto: {}", assignmentDto);

		return assignmentDto;
	}
}
