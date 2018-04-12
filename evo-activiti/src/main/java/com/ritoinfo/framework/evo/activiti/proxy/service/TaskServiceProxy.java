package com.ritoinfo.framework.evo.activiti.proxy.service;

import com.ritoinfo.framework.evo.activiti.condition.TaskCondition;
import com.ritoinfo.framework.evo.activiti.dto.VariableDto;
import com.ritoinfo.framework.evo.activiti.proxy.entity.CommentProxy;
import com.ritoinfo.framework.evo.activiti.proxy.entity.TaskProxy;
import com.ritoinfo.framework.evo.activiti.proxy.entity.VariableProxy;
import com.ritoinfo.framework.evo.activiti.proxy.model.ActivitiPage;
import com.ritoinfo.framework.evo.activiti.util.ActivitiUtil;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.JsonUtil;
import com.ritoinfo.framework.evo.common.uitl.SqlUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TaskServiceProxy {
	@Autowired
	private TaskService taskService;

	public TaskProxy getStartTask(String processInstanceId, String initiator) {
		TaskCondition condition = new TaskCondition();
		condition.setProcessInstanceId(processInstanceId);
		condition.setAssignee(initiator);
		return getOne(condition);
	}

	public TaskProxy getById(String taskId) {
		TaskCondition condition = new TaskCondition();
		condition.setTaskId(taskId);
		return getOne(condition);
	}

	public TaskProxy getOne(TaskCondition condition) {
		log.info("查询任务实例: " + condition);

		Task task = createQuery(condition, false).singleResult();
		return task == null ? null : toTaskProxy(task);
	}

	public int getCount(TaskCondition condition) {
		log.info("查询任务实例: " + condition);
		return new Long(createQuery(condition, false).count()).intValue();
	}

	public List<TaskProxy> getList(TaskCondition condition, ActivitiPage page) {
		log.info("查询任务实例: " + condition);
		return toTaskProxyList(createQuery(condition, true).listPage(page.getStart(), page.getSize()));
	}

	public void complete(String taskId, List<VariableProxy> variableList) {
		log.info("提交任务: " + taskId + ", " + JsonUtil.objectToJson(variableList));
		taskService.complete(taskId, ActivitiUtil.listToMap(variableList));
	}

	public void claim(String taskId, String assignee) {
		log.info("认领任务: " + taskId + ", " + assignee);
		taskService.claim(taskId, assignee);
	}

	public void delegate(String taskId, String assignee) {
		log.info("委派任务: " + taskId + ", " + assignee);
		taskService.delegateTask(taskId, assignee);
	}

	public void resolve(String taskId) {
		log.info("释放任务: " + taskId);
		taskService.resolveTask(taskId);
	}

	public List<CommentProxy> getProcessInstanceCommentList(String processInstanceId) {
		log.info("查询任务审批意见: " + processInstanceId);

		List<CommentProxy> commentProxyList = new ArrayList<>();

		for (Comment comment : taskService.getProcessInstanceComments(processInstanceId)) {
			CommentProxy commentProxy = new CommentProxy();
			BeanUtil.copy(commentProxy, comment);

			commentProxy.setAuthor(comment.getUserId());
			commentProxy.setMessage(comment.getFullMessage());

			commentProxyList.add(commentProxy);
		}

		return commentProxyList;
	}

	public CommentProxy saveComment(String processInstanceId, String taskId, String message) {
		log.info("增加审批意见: " + processInstanceId + ", " + taskId + ", " + message);

		Comment comment = taskService.addComment(taskId, processInstanceId, message);

		CommentProxy commentProxy = new CommentProxy();
		BeanUtil.copy(commentProxy, comment);

		return commentProxy;
	}

	public void saveTaskVariable(String taskId, List<VariableProxy> variableList) {
		taskService.setVariablesLocal(taskId, ActivitiUtil.listToMap(variableList));
	}

	private TaskQuery createQuery(TaskCondition condition, boolean needOrderBy) {
		TaskQuery query = taskService.createTaskQuery();

		String taskId = condition.getTaskId();
		if (StringUtil.isNotBlank(taskId)) {
			query.taskId(taskId);
		}
		String name = condition.getName();
		if (StringUtil.isNotBlank(name)) {
			query.taskName(name);
		}
		String nameLike = condition.getNameLike();
		if (StringUtil.isNotBlank(nameLike)) {
			query.taskNameLike(SqlUtil.toLike(nameLike));
		}
		String description = condition.getDescription();
		if (StringUtil.isNotBlank(description)) {
			query.taskDescription(description);
		}
		Integer priority = condition.getPriority();
		if (priority != null) {
			query.taskPriority(priority);
		}
		Integer minimumPriority = condition.getMinimumPriority();
		if (minimumPriority != null) {
			query.taskMinPriority(minimumPriority);
		}
		Integer maximumPriority = condition.getMaximumPriority();
		if (maximumPriority != null) {
			query.taskMaxPriority(maximumPriority);
		}
		String assignee = condition.getAssignee();
		if (StringUtil.isNotBlank(assignee)) {
			query.taskAssignee(assignee);
		}
		String assigneeLike = condition.getAssigneeLike();
		if (StringUtil.isNotBlank(assigneeLike)) {
			query.taskAssigneeLike(SqlUtil.toLike(assigneeLike));
		}
		String owner = condition.getOwner();
		if (StringUtil.isNotBlank(owner)) {
			query.taskOwner(owner);
		}
		String ownerLike = condition.getOwnerLike();
		if (StringUtil.isNotBlank(ownerLike)) {
			query.taskOwnerLike(SqlUtil.toLike(ownerLike));
		}
		//		.taskDelegationState(condition.getDelegationState()) TODO
		String candidateUser = condition.getCandidateUser();
		if (StringUtil.isNotBlank(candidateUser)) {
			query.taskCandidateUser(candidateUser);
		}
		String candidateGroup = condition.getCandidateGroup();
		if (StringUtil.isNotBlank(candidateGroup)) {
			query.taskCandidateGroup(candidateGroup);
		}
		//		.taskCandidateGroupIn(condition.getCandidateGroups()) TODO
		String involvedUser = condition.getInvolvedUser();
		if (StringUtil.isNotBlank(involvedUser)) {
			query.taskInvolvedUser(involvedUser);
		}
		String taskDefinitionKey = condition.getTaskDefinitionKey();
		if (StringUtil.isNotBlank(taskDefinitionKey)) {
			query.taskDefinitionKey(taskDefinitionKey);
		}
		String taskDefinitionKeyLike = condition.getTaskDefinitionKeyLike();
		if (StringUtil.isNotBlank(taskDefinitionKeyLike)) {
			query.taskDefinitionKeyLike(SqlUtil.toLike(taskDefinitionKeyLike));
		}
		String processInstanceId = condition.getProcessInstanceId();
		if (StringUtil.isNotBlank(processInstanceId)) {
			query.processInstanceId(processInstanceId);
		}
		String processInstanceBusinessKey = condition.getProcessInstanceBusinessKey();
		if (StringUtil.isNotBlank(processInstanceBusinessKey)) {
			query.processInstanceBusinessKey(processInstanceBusinessKey);
		}
		String processInstanceBusinessKeyLike = condition.getProcessInstanceBusinessKeyLike();
		if (StringUtil.isNotBlank(processInstanceBusinessKeyLike)) {
			query.processInstanceBusinessKeyLike(SqlUtil.toLike(processInstanceBusinessKeyLike));
		}
		String processDefinitionKey = condition.getProcessDefinitionKey();
		if (StringUtil.isNotBlank(processDefinitionKey)) {
			query.processDefinitionKey(processDefinitionKey);
		}
		String processDefinitionKeyLike = condition.getProcessDefinitionKeyLike();
		if (StringUtil.isNotBlank(processDefinitionKeyLike)) {
			query.processDefinitionKeyLike(SqlUtil.toLike(processDefinitionKeyLike));
		}
		String processDefinitionName = condition.getProcessDefinitionName();
		if (StringUtil.isNotBlank(processDefinitionName)) {
			query.processDefinitionName(processDefinitionName);
		}
		String processDefinitionNameLike = condition.getProcessDefinitionNameLike();
		if (StringUtil.isNotBlank(processDefinitionNameLike)) {
			query.processDefinitionNameLike(SqlUtil.toLike(processDefinitionNameLike));
		}
		String executionId = condition.getExecutionId();
		if (StringUtil.isNotBlank(executionId)) {
			query.executionId(executionId);
		}
		String createdOn = condition.getCreatedOn();
		if (StringUtil.isNotBlank(createdOn)) {
			query.taskCreatedOn(DateUtil.parseDatetime(createdOn));
		}
		String createdAfter = condition.getCreatedAfter();
		if (StringUtil.isNotBlank(createdAfter)) {
			query.taskCreatedAfter(DateUtil.parseDatetime(createdAfter));
		}
		String createdBefore = condition.getCreatedBefore();
		if (StringUtil.isNotBlank(createdBefore)) {
			query.taskCreatedBefore(DateUtil.parseDatetime(createdBefore));
		}
		String dueOn = condition.getDueOn();
		if (StringUtil.isNotBlank(dueOn)) {
			query.taskDueDate(DateUtil.parseDatetime(dueOn));
		}
		String dueAfter = condition.getDueAfter();
		if (StringUtil.isNotBlank(dueAfter)) {
			query.taskDueAfter(DateUtil.parseDatetime(dueAfter));
		}
		String dueBefore = condition.getDueBefore();
		if (StringUtil.isNotBlank(dueBefore)) {
			query.taskDueBefore(DateUtil.parseDatetime(dueBefore));
		}
		String tenantId = condition.getTenantId();
		if (StringUtil.isNotBlank(tenantId)) {
			query.taskTenantId(tenantId);
		}
		String tenantIdLike = condition.getTenantIdLike();
		if (StringUtil.isNotBlank(tenantIdLike)) {
			query.taskTenantIdLike(SqlUtil.toLike(tenantIdLike));
		}
		String candidateOrAssigned = condition.getCandidateOrAssigned();
		if (StringUtil.isNotBlank(candidateOrAssigned)) {
			query.taskCandidateOrAssigned(candidateOrAssigned);
		}
		Boolean unassigned = condition.getUnassigned();
		if (unassigned != null && unassigned) {
			query.taskUnassigned();
		}
		Boolean withoutDueDate = condition.getWithoutDueDate();
		if (withoutDueDate != null && withoutDueDate) {
			query.withoutTaskDueDate();
		}
		Boolean excludeSubTasks = condition.getExcludeSubTasks();
		if (excludeSubTasks != null && excludeSubTasks) {
			query.excludeSubtasks();
		}
		Boolean active = condition.getActive();
		if (active != null && active) {
			query.active();
		}
		Boolean includeTaskLocalVariables = condition.getIncludeTaskLocalVariables();
		if (includeTaskLocalVariables != null && includeTaskLocalVariables) {
			query.includeTaskLocalVariables();
		}
		Boolean includeProcessVariables = condition.getIncludeProcessVariables();
		if (includeProcessVariables != null && includeProcessVariables) {
			query.includeProcessVariables();
		}
		Boolean withoutTenantId = condition.getWithoutTenantId();
		if (withoutTenantId != null && withoutTenantId) {
			query.taskWithoutTenantId();
		}

		List<VariableDto> processInstanceVariables = condition.getProcessInstanceVariables();
		if (processInstanceVariables != null) {
			for (VariableDto variableDto : processInstanceVariables) {
				query.processVariableValueEquals(variableDto.getName(), variableDto.getValue());
			}
		}
		List<VariableDto> taskVariables = condition.getTaskVariables();
		if (taskVariables != null) {
			for (VariableDto variableDto : taskVariables) {
				query.taskVariableValueEquals(variableDto.getName(), variableDto.getValue());
			}
		}

		if (needOrderBy) {
			String pageSort = condition.getPageSort();
			if (StringUtil.isNotBlank(pageSort)) {
				if ("dueDateNullsFirst".equals(pageSort)) {
					query.orderByDueDateNullsFirst();
				} else if ("dueDateNullsLast".equals(pageSort)) {
					query.orderByDueDateNullsLast();
				} else if ("executionId".equals(pageSort)) {
					query.orderByExecutionId();
				} else if ("processDefinitionId".equals(pageSort)) {
					query.orderByProcessDefinitionId();
				} else if ("processInstanceId".equals(pageSort)) {
					query.orderByProcessInstanceId();
				} else if ("taskAssignee".equals(pageSort)) {
					query.orderByTaskAssignee();
				} else if ("taskCreateTime".equals(pageSort)) {
					query.orderByTaskCreateTime();
				} else if ("taskDefinitionKey".equals(pageSort)) {
					query.orderByTaskDefinitionKey();
				} else if ("taskDescription".equals(pageSort)) {
					query.orderByTaskDescription();
				} else if ("taskDueDate".equals(pageSort)) {
					query.orderByTaskDueDate();
				} else if ("taskId".equals(pageSort)) {
					query.orderByTaskId();
				} else if ("taskName".equals(pageSort)) {
					query.orderByTaskName();
				} else if ("taskOwner".equals(pageSort)) {
					query.orderByTaskOwner();
				} else if ("taskPriority".equals(pageSort)) {
					query.orderByTaskPriority();
				} else if ("tenantId".equals(pageSort)) {
					query.orderByTenantId();
				}

				if ("asc".equals(condition.getPageOrder())) {
					query.asc();
				} else {
					query.desc();
				}
			}
		}

		return query;
	}

	private List<TaskProxy> toTaskProxyList(List<Task> taskList) {
		List<TaskProxy> taskProxyList = new ArrayList<>();

		for (Task task : taskList) {
			taskProxyList.add(toTaskProxy(task));
		}

		return taskProxyList;
	}

	private TaskProxy toTaskProxy(Task task) {
		TaskProxy taskProxy = new TaskProxy();
		BeanUtil.copy(taskProxy, task);

		Map<String, Object> variableMap = task.getProcessVariables();
		List<VariableProxy> variableProxyList;
		if (variableMap == null) {
			variableProxyList = new ArrayList<>();
		} else {
			variableProxyList = ActivitiUtil.mapToList(variableMap);
		}
		taskProxy.setVariables(variableProxyList);

		return taskProxy;
	}
}
