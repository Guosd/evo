package com.github.framework.evo.flowable.bizz;

import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.common.uitl.CollectionUtil;
import com.github.framework.evo.common.uitl.StringUtil;
import com.github.framework.evo.flowable.model.OrderQueryCondition;
import com.github.framework.evo.flowable.model.TaskDto;
import com.github.framework.evo.flowable.model.TaskInfoDto;
import com.github.framework.evo.flowable.model.TaskInfoQueryCondition;
import com.github.framework.evo.flowable.model.VariableQueryCondition;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.TaskInfoQuery;
import org.flowable.task.api.TaskInfoQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2019-03-25 10:29
 */
@Service
public class TaskBizz {
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;

	public List<TaskInfoDto> findPage(TaskInfoQueryCondition taskInfoQueryCondition) {
		TaskInfoQueryWrapper taskInfoQueryWrapper;
		if (taskInfoQueryCondition.isRuntimeQuery()) {
			taskInfoQueryWrapper = new TaskInfoQueryWrapper(taskService.createTaskQuery());
		} else {
			taskInfoQueryWrapper = new TaskInfoQueryWrapper(historyService.createHistoricTaskInstanceQuery());
		}

		TaskInfoQuery<? extends TaskInfoQuery<?, ?>, ? extends TaskInfo> taskInfoQuery = taskInfoQueryWrapper.getTaskInfoQuery();

		String caseDefinitionId = taskInfoQueryCondition.getCaseDefinitionId();
		if (StringUtil.isNotBlank(caseDefinitionId)) {
			taskInfoQuery.caseDefinitionId(caseDefinitionId);
		}
		String caseInstanceId = taskInfoQueryCondition.getCaseInstanceId();
		if (StringUtil.isNotBlank(caseInstanceId)) {
			taskInfoQuery.caseInstanceId(caseInstanceId);
		}
		String caseInstanceIdWithChildren = taskInfoQueryCondition.getCaseInstanceIdWithChildren();
		if (StringUtil.isNotBlank(caseInstanceIdWithChildren)) {
			taskInfoQuery.caseInstanceIdWithChildren(caseInstanceIdWithChildren);
		}
		String cmmnDeploymentId = taskInfoQueryCondition.getCmmnDeploymentId();
		if (StringUtil.isNotBlank(cmmnDeploymentId)) {
			taskInfoQuery.cmmnDeploymentId(cmmnDeploymentId);
		}
		List<String> cmmnDeploymentIds = taskInfoQueryCondition.getCmmnDeploymentIds();
		if (CollectionUtil.isNotEmpty(cmmnDeploymentIds)) {
			taskInfoQuery.cmmnDeploymentIdIn(cmmnDeploymentIds);
		}
		String deploymentId = taskInfoQueryCondition.getDeploymentId();
		if (StringUtil.isNotBlank(deploymentId)) {
			taskInfoQuery.deploymentId(deploymentId);
		}
		List<String> deploymentIds = taskInfoQueryCondition.getDeploymentIds();
		if (CollectionUtil.isNotEmpty(deploymentIds)) {
			taskInfoQuery.deploymentIdIn(deploymentIds);
		}
		String executionId = taskInfoQueryCondition.getExecutionId();
		if (StringUtil.isNotBlank(executionId)) {
			taskInfoQuery.executionId(executionId);
		}
		if (taskInfoQueryCondition.isIgnoreAssigneeValue()) {
			taskInfoQuery.ignoreAssigneeValue();
		}
		if (taskInfoQueryCondition.isIncludeIdentityLinks()) {
			taskInfoQuery.includeIdentityLinks();
		}
		if (taskInfoQueryCondition.isIncludeProcessVariables()) {
			taskInfoQuery.includeProcessVariables();
		}
		if (taskInfoQueryCondition.isIncludeTaskLocalVariables()) {
			taskInfoQuery.includeTaskLocalVariables();
		}
		Integer limitTaskVariables = taskInfoQueryCondition.getLimitTaskVariables();
		if (limitTaskVariables != null) {
			taskInfoQuery.limitTaskVariables(limitTaskVariables);
		}
		String locale = taskInfoQueryCondition.getLocale();
		if (StringUtil.isNotBlank(locale)) {
			taskInfoQuery.locale(locale);
		}
		String planItemInstanceId = taskInfoQueryCondition.getPlanItemInstanceId();
		if (StringUtil.isNotBlank(planItemInstanceId)) {
			taskInfoQuery.planItemInstanceId(planItemInstanceId);
		}
		List<String> processCategoryInList = taskInfoQueryCondition.getProcessCategoryInList();
		if (CollectionUtil.isNotEmpty(processCategoryInList)) {
			taskInfoQuery.processCategoryIn(processCategoryInList);
		}
		List<String> processCategoryNotInList = taskInfoQueryCondition.getProcessCategoryNotInList();
		if (CollectionUtil.isNotEmpty(processCategoryNotInList)) {
			taskInfoQuery.processCategoryNotIn(processCategoryNotInList);
		}
		String processDefinitionId = taskInfoQueryCondition.getProcessDefinitionId();
		if (StringUtil.isNotBlank(processDefinitionId)) {
			taskInfoQuery.processDefinitionId(processDefinitionId);
		}
		String processDefinitionKey = taskInfoQueryCondition.getProcessDefinitionKey();
		if (StringUtil.isNotBlank(processDefinitionKey)) {
			taskInfoQuery.processDefinitionKey(processDefinitionKey);
		}
		List<String> processDefinitionKeys = taskInfoQueryCondition.getProcessDefinitionKeys();
		if (CollectionUtil.isNotEmpty(processDefinitionKeys)) {
			taskInfoQuery.processDefinitionKeyIn(processDefinitionKeys);
		}
		String processDefinitionKeyLike = taskInfoQueryCondition.getProcessDefinitionKeyLike();
		if (StringUtil.isNotBlank(processDefinitionKeyLike)) {
			taskInfoQuery.processDefinitionKeyLike(processDefinitionKeyLike);
		}
		String processDefinitionKeyLikeIgnoreCase = taskInfoQueryCondition.getProcessDefinitionKeyLikeIgnoreCase();
		if (StringUtil.isNotBlank(processDefinitionKeyLikeIgnoreCase)) {
			taskInfoQuery.processDefinitionKeyLikeIgnoreCase(processDefinitionKeyLikeIgnoreCase);
		}
		String processDefinitionName = taskInfoQueryCondition.getProcessDefinitionName();
		if (StringUtil.isNotBlank(processDefinitionName)) {
			taskInfoQuery.processDefinitionName(processDefinitionName);
		}
		String processDefinitionNameLike = taskInfoQueryCondition.getProcessDefinitionNameLike();
		if (StringUtil.isNotBlank(processDefinitionNameLike)) {
			taskInfoQuery.processDefinitionNameLike(processDefinitionNameLike);
		}
		String processInstanceBusinessKey = taskInfoQueryCondition.getProcessInstanceBusinessKey();
		if (StringUtil.isNotBlank(processInstanceBusinessKey)) {
			taskInfoQuery.processInstanceBusinessKey(processInstanceBusinessKey);
		}
		String processInstanceBusinessKeyLike = taskInfoQueryCondition.getProcessInstanceBusinessKeyLike();
		if (StringUtil.isNotBlank(processInstanceBusinessKeyLike)) {
			taskInfoQuery.processInstanceBusinessKeyLike(processInstanceBusinessKeyLike);
		}
		String processInstanceBusinessKeyLikeIgnoreCase = taskInfoQueryCondition.getProcessInstanceBusinessKeyLikeIgnoreCase();
		if (StringUtil.isNotBlank(processInstanceBusinessKeyLikeIgnoreCase)) {
			taskInfoQuery.processInstanceBusinessKeyLikeIgnoreCase(processInstanceBusinessKeyLikeIgnoreCase);
		}
		String processInstanceId = taskInfoQueryCondition.getProcessInstanceId();
		if (StringUtil.isNotBlank(processInstanceId)) {
			taskInfoQuery.processInstanceId(processInstanceId);
		}
		List<String> processInstanceIds = taskInfoQueryCondition.getProcessInstanceIds();
		if (CollectionUtil.isNotEmpty(processInstanceIds)) {
			taskInfoQuery.processInstanceIdIn(processInstanceIds);
		}
		String processInstanceIdWithChildren = taskInfoQueryCondition.getProcessInstanceIdWithChildren();
		if (StringUtil.isNotBlank(processInstanceIdWithChildren)) {
			taskInfoQuery.processInstanceIdWithChildren(processInstanceIdWithChildren);
		}
		List<VariableQueryCondition> processVariableQueryConditions = taskInfoQueryCondition.getProcessVariableQueryConditions();
		if (CollectionUtil.isNotEmpty(processVariableQueryConditions)) {
			for (VariableQueryCondition variableQueryCondition : processVariableQueryConditions) {
				String name = variableQueryCondition.getName();
				Object value = variableQueryCondition.getValue();
				String operator = variableQueryCondition.getOperator();
				if (StringUtil.isNotBlank(name)) {
					if (value == null || StringUtil.isBlank(value.toString())) {
						if ("exists".equals(operator)) {
							taskInfoQuery.processVariableExists(name);
						} else if ("notExists".equals(operator)) {
							taskInfoQuery.processVariableNotExists(name);
						}
					}

					if ("equals".equals(operator)) {
						taskInfoQuery.processVariableValueEquals(name, value);
					} else if ("equalsIgnoreCase".equals(operator)) {
						taskInfoQuery.processVariableValueEqualsIgnoreCase(name, StringUtil.toStringIfNull(value));
					} else if ("greaterThan".equals(operator)) {
						taskInfoQuery.processVariableValueGreaterThan(name, value);
					} else if ("greaterThanOrEqual".equals(operator)) {
						taskInfoQuery.processVariableValueGreaterThanOrEqual(name, value);
					} else if ("lessThan".equals(operator)) {
						taskInfoQuery.processVariableValueLessThan(name, value);
					} else if ("lessThanOrEqual".equals(operator)) {
						taskInfoQuery.processVariableValueLessThanOrEqual(name, value);
					} else if ("like".equals(operator)) {
						taskInfoQuery.processVariableValueLike(name, StringUtil.toStringIfNull(value));
					} else if ("likeIgnoreCase".equals(operator)) {
						taskInfoQuery.processVariableValueLikeIgnoreCase(name, StringUtil.toStringIfNull(value));
					} else if ("notEquals".equals(operator)) {
						taskInfoQuery.processVariableValueNotEquals(name, value);
					} else if ("notEqualsIgnoreCase".equals(operator)) {
						taskInfoQuery.processVariableValueNotEqualsIgnoreCase(name, StringUtil.toStringIfNull(value));
					}
				} else {
					if ("equals".equals(operator)) {
						taskInfoQuery.processVariableValueEquals(value);
					}
				}
			}
		}
		String scopeDefinitionId = taskInfoQueryCondition.getScopeDefinitionId();
		if (StringUtil.isNotBlank(scopeDefinitionId)) {
			taskInfoQuery.scopeDefinitionId(scopeDefinitionId);
		}
		String scopeId = taskInfoQueryCondition.getScopeId();
		if (StringUtil.isNotBlank(scopeId)) {
			taskInfoQuery.scopeId(scopeId);
		}
		String scopeType = taskInfoQueryCondition.getScopeType();
		if (StringUtil.isNotBlank(scopeType)) {
			taskInfoQuery.scopeType(scopeType);
		}
		String subScopeId = taskInfoQueryCondition.getSubScopeId();
		if (StringUtil.isNotBlank(subScopeId)) {
			taskInfoQuery.subScopeId(subScopeId);
		}
		String taskAssignee = taskInfoQueryCondition.getTaskAssignee();
		if (StringUtil.isNotBlank(taskAssignee)) {
			taskInfoQuery.taskAssignee(taskAssignee);
		}
		List<String> taskAssigneeIds = taskInfoQueryCondition.getDeploymentIds();
		if (CollectionUtil.isNotEmpty(taskAssigneeIds)) {
			taskInfoQuery.taskAssigneeIds(taskAssigneeIds);
		}
		String taskAssigneeLike = taskInfoQueryCondition.getTaskAssigneeLike();
		if (StringUtil.isNotBlank(taskAssigneeLike)) {
			taskInfoQuery.taskAssigneeLike(taskAssigneeLike);
		}
		String taskAssigneeLikeIgnoreCase = taskInfoQueryCondition.getTaskAssigneeLikeIgnoreCase();
		if (StringUtil.isNotBlank(taskAssigneeLikeIgnoreCase)) {
			taskInfoQuery.taskAssigneeLikeIgnoreCase(taskAssigneeLikeIgnoreCase);
		}
		String taskCandidateGroup = taskInfoQueryCondition.getTaskCandidateGroup();
		if (StringUtil.isNotBlank(taskCandidateGroup)) {
			taskInfoQuery.taskCandidateGroup(taskCandidateGroup);
		}
		List<String> taskCandidateGroups = taskInfoQueryCondition.getTaskCandidateGroups();
		if (CollectionUtil.isNotEmpty(taskCandidateGroups)) {
			taskInfoQuery.taskCandidateGroupIn(taskCandidateGroups);
		}
		String taskCandidateUser = taskInfoQueryCondition.getTaskCandidateUser();
		if (StringUtil.isNotBlank(taskCandidateUser)) {
			taskInfoQuery.taskCandidateUser(taskCandidateUser);
		}
		String taskCategory = taskInfoQueryCondition.getTaskCategory();
		if (StringUtil.isNotBlank(taskCategory)) {
			taskInfoQuery.taskCategory(taskCategory);
		}
		Date taskCreatedAfter = taskInfoQueryCondition.getTaskCreatedAfter();
		if (taskCreatedAfter != null) {
			taskInfoQuery.taskCreatedAfter(taskCreatedAfter);
		}
		Date taskCreatedBefore = taskInfoQueryCondition.getTaskCreatedBefore();
		if (taskCreatedBefore != null) {
			taskInfoQuery.taskCreatedBefore(taskCreatedBefore);
		}
		Date taskCreatedOn = taskInfoQueryCondition.getTaskCreatedOn();
		if (taskCreatedOn != null) {
			taskInfoQuery.taskCreatedOn(taskCreatedOn);
		}
		String taskDefinitionId = taskInfoQueryCondition.getTaskDefinitionId();
		if (StringUtil.isNotBlank(taskDefinitionId)) {
			taskInfoQuery.taskDefinitionId(taskDefinitionId);
		}
		String taskDefinitionKey = taskInfoQueryCondition.getTaskDefinitionKey();
		if (StringUtil.isNotBlank(taskDefinitionKey)) {
			taskInfoQuery.taskDefinitionKey(taskDefinitionKey);
		}
		String taskDefinitionKeyLike = taskInfoQueryCondition.getTaskDefinitionKeyLike();
		if (StringUtil.isNotBlank(taskDefinitionKeyLike)) {
			taskInfoQuery.taskDefinitionKeyLike(taskDefinitionKeyLike);
		}
		String taskDescription = taskInfoQueryCondition.getTaskDescription();
		if (StringUtil.isNotBlank(taskDescription)) {
			taskInfoQuery.taskDescription(taskDescription);
		}
		String taskDescriptionLike = taskInfoQueryCondition.getTaskDescriptionLike();
		if (StringUtil.isNotBlank(taskDescriptionLike)) {
			taskInfoQuery.taskDescriptionLike(taskDescriptionLike);
		}
		String taskDescriptionLikeIgnoreCase = taskInfoQueryCondition.getTaskDescriptionLikeIgnoreCase();
		if (StringUtil.isNotBlank(taskDescriptionLikeIgnoreCase)) {
			taskInfoQuery.taskDescriptionLikeIgnoreCase(taskDescriptionLikeIgnoreCase);
		}
		Date taskDueAfter = taskInfoQueryCondition.getTaskDueAfter();
		if (taskDueAfter != null) {
			taskInfoQuery.taskDueAfter(taskDueAfter);
		}
		Date taskDueBefore = taskInfoQueryCondition.getTaskDueBefore();
		if (taskDueBefore != null) {
			taskInfoQuery.taskDueBefore(taskDueBefore);
		}
		Date taskDueDate = taskInfoQueryCondition.getTaskDueDate();
		if (taskDueDate != null) {
			taskInfoQuery.taskDueDate(taskDueDate);
		}
		String taskId = taskInfoQueryCondition.getTaskId();
		if (StringUtil.isNotBlank(taskId)) {
			taskInfoQuery.taskId(taskId);
		}
		Set<String> taskInvolvedGroups = taskInfoQueryCondition.getTaskInvolvedGroups();
		if (CollectionUtil.isNotEmpty(taskInvolvedGroups)) {
			taskInfoQuery.taskInvolvedGroups(taskInvolvedGroups);
		}
		String taskInvolvedUser = taskInfoQueryCondition.getTaskInvolvedUser();
		if (StringUtil.isNotBlank(taskInvolvedUser)) {
			taskInfoQuery.taskInvolvedUser(taskInvolvedUser);
		}
		Integer taskMaxPriority = taskInfoQueryCondition.getTaskMaxPriority();
		if (taskMaxPriority != null) {
			taskInfoQuery.taskMaxPriority(taskMaxPriority);
		}
		Integer taskMinPriority = taskInfoQueryCondition.getTaskMinPriority();
		if (taskMinPriority != null) {
			taskInfoQuery.taskMinPriority(taskMinPriority);
		}
		String taskName = taskInfoQueryCondition.getTaskName();
		if (StringUtil.isNotBlank(taskName)) {
			taskInfoQuery.taskName(taskName);
		}
		List<String> taskNames = taskInfoQueryCondition.getTaskNames();
		if (CollectionUtil.isNotEmpty(taskNames)) {
			taskInfoQuery.taskNameIn(taskNames);
		}
		List<String> taskNameInIgnoreCases = taskInfoQueryCondition.getTaskNameInIgnoreCases();
		if (CollectionUtil.isNotEmpty(taskNameInIgnoreCases)) {
			taskInfoQuery.taskNameInIgnoreCase(taskNameInIgnoreCases);
		}
		String taskNameLike = taskInfoQueryCondition.getTaskNameLike();
		if (StringUtil.isNotBlank(taskNameLike)) {
			taskInfoQuery.taskNameLike(taskNameLike);
		}
		String taskNameLikeIgnoreCase = taskInfoQueryCondition.getTaskNameLikeIgnoreCase();
		if (StringUtil.isNotBlank(taskNameLikeIgnoreCase)) {
			taskInfoQuery.taskNameLikeIgnoreCase(taskNameLikeIgnoreCase);
		}
		String taskOwner = taskInfoQueryCondition.getTaskOwner();
		if (StringUtil.isNotBlank(taskOwner)) {
			taskInfoQuery.taskOwner(taskOwner);
		}
		String taskOwnerLike = taskInfoQueryCondition.getTaskOwnerLike();
		if (StringUtil.isNotBlank(taskOwnerLike)) {
			taskInfoQuery.taskOwnerLike(taskOwnerLike);
		}
		String taskOwnerLikeIgnoreCase = taskInfoQueryCondition.getTaskOwnerLikeIgnoreCase();
		if (StringUtil.isNotBlank(taskOwnerLikeIgnoreCase)) {
			taskInfoQuery.taskOwnerLikeIgnoreCase(taskOwnerLikeIgnoreCase);
		}
		Integer taskPriority = taskInfoQueryCondition.getTaskPriority();
		if (taskPriority != null) {
			taskInfoQuery.taskPriority(taskPriority);
		}
		String taskTenantId = taskInfoQueryCondition.getTaskTenantId();
		if (StringUtil.isNotBlank(taskTenantId)) {
			taskInfoQuery.taskTenantId(taskTenantId);
		}
		String taskTenantIdLike = taskInfoQueryCondition.getTaskTenantIdLike();
		if (StringUtil.isNotBlank(taskTenantIdLike)) {
			taskInfoQuery.taskTenantIdLike(taskTenantIdLike);
		}
		List<VariableQueryCondition> taskVariableQueryConditions = taskInfoQueryCondition.getProcessVariableQueryConditions();
		if (CollectionUtil.isNotEmpty(taskVariableQueryConditions)) {
			for (VariableQueryCondition variableQueryCondition : taskVariableQueryConditions) {
				String name = variableQueryCondition.getName();
				Object value = variableQueryCondition.getValue();
				String operator = variableQueryCondition.getOperator();
				if (StringUtil.isNotBlank(name)) {
					if (value == null || StringUtil.isBlank(value.toString())) {
						if ("exists".equals(operator)) {
							taskInfoQuery.taskVariableExists(name);
						} else if ("notExists".equals(operator)) {
							taskInfoQuery.taskVariableNotExists(name);
						}
					}

					if ("equals".equals(operator)) {
						taskInfoQuery.taskVariableValueEquals(name, value);
					} else if ("equalsIgnoreCase".equals(operator)) {
						taskInfoQuery.taskVariableValueEqualsIgnoreCase(name, StringUtil.toStringIfNull(value));
					} else if ("greaterThan".equals(operator)) {
						taskInfoQuery.taskVariableValueGreaterThan(name, value);
					} else if ("greaterThanOrEqual".equals(operator)) {
						taskInfoQuery.taskVariableValueGreaterThanOrEqual(name, value);
					} else if ("lessThan".equals(operator)) {
						taskInfoQuery.taskVariableValueLessThan(name, value);
					} else if ("lessThanOrEqual".equals(operator)) {
						taskInfoQuery.taskVariableValueLessThanOrEqual(name, value);
					} else if ("like".equals(operator)) {
						taskInfoQuery.taskVariableValueLike(name, StringUtil.toStringIfNull(value));
					} else if ("likeIgnoreCase".equals(operator)) {
						taskInfoQuery.taskVariableValueLikeIgnoreCase(name, StringUtil.toStringIfNull(value));
					} else if ("notEquals".equals(operator)) {
						taskInfoQuery.taskVariableValueNotEquals(name, value);
					} else if ("notEqualsIgnoreCase".equals(operator)) {
						taskInfoQuery.taskVariableValueNotEqualsIgnoreCase(name, StringUtil.toStringIfNull(value));
					}
				} else {
					if ("equals".equals(operator)) {
						taskInfoQuery.taskVariableValueEquals(value);
					}
				}
			}
		}
		if (taskInfoQueryCondition.isTaskWithFormKey()) {
			taskInfoQuery.taskWithFormKey();
		}
		if (taskInfoQueryCondition.isTaskWithoutTenantId()) {
			taskInfoQuery.taskWithoutTenantId();
		}
		if (taskInfoQueryCondition.isWithLocalizationFallback()) {
			taskInfoQuery.withLocalizationFallback();
		}
		if (taskInfoQueryCondition.isWithoutTaskDueDate()) {
			taskInfoQuery.withoutTaskDueDate();
		}
		List<OrderQueryCondition> orders = taskInfoQueryCondition.getOrders();
		if (CollectionUtil.isNotEmpty(orders)) {
			for (OrderQueryCondition order : orders) {
				String property = order.getProperty();
				String direction = order.getDirection();
				if ("dueDateNullsFirst".equals(property)) {
					taskInfoQuery.orderByDueDateNullsFirst();
				} else if ("executionId".equals(property)) {
					taskInfoQuery.orderByExecutionId();
				} else if ("processDefinitionId".equals(property)) {
					taskInfoQuery.orderByProcessDefinitionId();
				} else if ("processInstanceId".equals(property)) {
					taskInfoQuery.orderByProcessInstanceId();
				} else if ("taskAssignee".equals(property)) {
					taskInfoQuery.orderByTaskAssignee();
				} else if ("taskCreateTime".equals(property)) {
					taskInfoQuery.orderByTaskCreateTime();
				} else if ("taskDefinitionKey".equals(property)) {
					taskInfoQuery.orderByTaskDefinitionKey();
				} else if ("taskDescription".equals(property)) {
					taskInfoQuery.orderByTaskDescription();
				} else if ("taskDueDate".equals(property)) {
					taskInfoQuery.orderByTaskDueDate();
				} else if ("taskId".equals(property)) {
					taskInfoQuery.orderByTaskId();
				} else if ("taskName".equals(property)) {
					taskInfoQuery.orderByTaskName();
				} else if ("taskOwner".equals(property)) {
					taskInfoQuery.orderByTaskOwner();
				} else if ("taskPriority".equals(property)) {
					taskInfoQuery.orderByTaskPriority();
				} else if ("tenantId".equals(property)) {
					taskInfoQuery.orderByTenantId();
				}
				if ("asc".equals(direction)) {
					taskInfoQuery.asc();
				} else if ("desc".equals(direction)){
					taskInfoQuery.desc();
				}
			}
		}

		return BaseHelper.convertObject(
				taskInfoQuery.listPage(taskInfoQueryCondition.getFirstResult(), taskInfoQueryCondition.getMaxResults()),
				TaskInfoDto.class);
	}

	public void complete(TaskDto taskDto) {
		taskService.addComment(taskDto.getTaskId(), taskDto.getProcessInstanceId(), taskDto.getMessage());
		taskService.complete(taskDto.getTaskId(), taskDto.getVariables());
	}
}
