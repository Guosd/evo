package com.github.framework.evo.flowable.bizz;

import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.flowable.model.CommentDto;
import com.github.framework.evo.flowable.model.ProcessInstanceDto;
import com.github.framework.evo.flowable.model.StartReq;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-03-24 12:19
 */
@Slf4j
@Service
public class RuntimeBizz {
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	public ProcessInstance getProcessInstanceByIdForRaw(String id) {
		return runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
	}

	public ProcessInstanceDto startProcessInstanceByKey(StartReq startReq) {
		Authentication.setAuthenticatedUserId(startReq.getInitiator());
		return BaseHelper.convertObject(
				runtimeService.startProcessInstanceByKey(startReq.getProcessDefinitionKey(), startReq.getBusinessKey(), startReq.getVariables()),
				ProcessInstanceDto.class);
	}

	@Transactional
	public ProcessInstanceDto startProcessInstanceByKeyAndNext(StartReq startReq) {
		Authentication.setAuthenticatedUserId(startReq.getInitiator());

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startReq.getProcessDefinitionKey(), startReq.getBusinessKey(), startReq.getVariables());
		log.info("ProcessInstance: {}", processInstance);

		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		Comment comment = taskService.addComment(task.getId(), task.getProcessInstanceId(), startReq.getMessage());
		log.info("Comment: {}", comment);

		taskService.complete(task.getId(), startReq.getVariables());

		return BaseHelper.convertObject(processInstance, ProcessInstanceDto.class);
	}

	public List<CommentDto> findProcessInstanceComments(String processInstanceId) {
		return BaseHelper.convertObject(taskService.getProcessInstanceComments(processInstanceId), CommentDto.class);
	}

	public void clear() {
		for (ProcessInstance processInstance : runtimeService.createProcessInstanceQuery().active().list()) {
			runtimeService.deleteProcessInstance(processInstance.getId(), "Clear");
		}
	}
}
