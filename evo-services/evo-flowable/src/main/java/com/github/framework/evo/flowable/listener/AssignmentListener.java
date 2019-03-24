package com.github.framework.evo.flowable.listener;

import com.github.framework.evo.flowable.model.AssignmentDto;
import org.flowable.engine.delegate.TaskListener;
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
@Component
public class AssignmentListener implements TaskListener {
	@Autowired
	private RestTemplate restTemplate;

	private String service;
	private String uri;

	private static HttpHeaders headers = new HttpHeaders();

	static {
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		AssignmentDto assignmentDto = restTemplate.postForObject(service, new HttpEntity<>(delegateTask.getVariables(), headers), AssignmentDto.class);
		delegateTask.addCandidateUsers(assignmentDto.getActors());
	}
}
