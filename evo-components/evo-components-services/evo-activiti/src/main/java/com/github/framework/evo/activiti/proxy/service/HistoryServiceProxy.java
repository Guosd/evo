package com.github.framework.evo.activiti.proxy.service;

import com.github.framework.evo.activiti.condition.HistoricTaskInstanceCondition;
import com.github.framework.evo.activiti.dto.VariableDto;
import com.github.framework.evo.activiti.proxy.entity.HistoricTaskInstanceProxy;
import com.github.framework.evo.activiti.proxy.entity.VariableProxy;
import com.github.framework.evo.activiti.proxy.model.ActivitiPage;
import com.github.framework.evo.activiti.util.ActivitiUtil;
import com.github.framework.evo.common.uitl.BeanUtil;
import com.github.framework.evo.common.uitl.DateUtil;
import com.github.framework.evo.common.uitl.SqlUtil;
import com.github.framework.evo.common.uitl.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HistoryServiceProxy {
	@Autowired
	private HistoryService historyService;

	public HistoricTaskInstanceProxy getById(String taskId) {
		HistoricTaskInstanceCondition condition = new HistoricTaskInstanceCondition();
		condition.setTaskId(taskId);
		return getOne(condition);
	}

	public HistoricTaskInstanceProxy getOne(HistoricTaskInstanceCondition condition) {
		log.info("查询历史任务实例: " + condition);

		HistoricTaskInstance historicTaskInstance = createHistoricTaskInstanceQuery(condition, false).singleResult();
		return historicTaskInstance == null ? null : toHistoricTaskInstanceProxy(historicTaskInstance);
	}

	public int getHistoricTaskInstanceCount(HistoricTaskInstanceCondition condition) {
		log.info("查询历史任务实例: " + condition);
		return new Long(createHistoricTaskInstanceQuery(condition, false).count()).intValue();
	}

	public List<HistoricTaskInstanceProxy> getHistoricTaskInstanceList(HistoricTaskInstanceCondition condition) {
		log.info("查询历史任务实例: " + condition);
		return toHistoricTaskInstanceProxyList(createHistoricTaskInstanceQuery(condition, true).list());
	}

	public List<HistoricTaskInstanceProxy> getHistoricTaskInstanceList(HistoricTaskInstanceCondition condition, ActivitiPage page) {
		log.info("查询历史任务实例: " + condition);
		return toHistoricTaskInstanceProxyList(createHistoricTaskInstanceQuery(condition, true).listPage(page.getStart(), page.getSize()));
	}

	private HistoricTaskInstanceQuery createHistoricTaskInstanceQuery(HistoricTaskInstanceCondition condition, boolean needOrderBy) {
		HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();

		String taskId = condition.getTaskId();
		if (StringUtil.isNotBlank(taskId)) {
			query.taskId(taskId);
		}
		String processInstanceId = condition.getProcessInstanceId();
		if (StringUtil.isNotBlank(processInstanceId)) {
			query.processInstanceId(processInstanceId);
		}
		String processBusinessKey = condition.getProcessBusinessKey();
		if (StringUtil.isNotBlank(processBusinessKey)) {
			query.processInstanceBusinessKey(processBusinessKey);
		}
		String processBusinessKeyLike = condition.getProcessBusinessKeyLike();
		if (StringUtil.isNotBlank(processBusinessKeyLike)) {
			query.processInstanceBusinessKeyLike(SqlUtil.toLike(processBusinessKeyLike));
		}
		String processDefinitionId = condition.getProcessDefinitionId();
		if (StringUtil.isNotBlank(processDefinitionId)) {
			query.processDefinitionId(processDefinitionId);
		}
		String processDefinitionKey = condition.getProcessDefinitionKey();
		if (StringUtil.isNotBlank(processBusinessKey)) {
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
		String taskName = condition.getTaskName();
		if (StringUtil.isNotBlank(taskName)) {
			query.taskName(taskName);
		}
		String taskNameLike = condition.getTaskNameLike();
		if (StringUtil.isNotBlank(taskNameLike)) {
			query.taskNameLike(SqlUtil.toLike(taskNameLike));
		}
		String taskDescription = condition.getTaskDescription();
		if (StringUtil.isNotBlank(taskDescription)) {
			query.taskDescription(taskDescription);
		}
		String taskDescriptionLike = condition.getTaskDescriptionLike();
		if (StringUtil.isNotBlank(taskDescriptionLike)) {
			query.taskDescriptionLike(SqlUtil.toLike(taskDescriptionLike));
		}
		String taskDeleteReason = condition.getTaskDeleteReason();
		if (StringUtil.isNotBlank(taskDeleteReason)) {
			query.taskDeleteReason(taskDeleteReason);
		}
		String taskDeleteReasonLike = condition.getTaskDeleteReasonLike();
		if (StringUtil.isNotBlank(taskDeleteReasonLike)) {
			query.taskDeleteReasonLike(SqlUtil.toLike(taskDeleteReasonLike));
		}
		String taskDefinitionKey = condition.getTaskDefinitionKey();
		if (StringUtil.isNotBlank(taskDefinitionKey)) {
			query.taskDefinitionKey(taskDefinitionKey);
		}
		String taskDefinitionKeyLike = condition.getTaskDefinitionKeyLike();
		if (StringUtil.isNotBlank(taskDefinitionKeyLike)) {
			query.taskDefinitionKeyLike(SqlUtil.toLike(taskDefinitionKeyLike));
		}
		String taskAssignee = condition.getTaskAssignee();
		if (StringUtil.isNotBlank(taskAssignee)) {
			query.taskAssignee(taskAssignee);
		}
		String taskAssigneeLike = condition.getTaskAssigneeLike();
		if (StringUtil.isNotBlank(taskAssigneeLike)) {
			query.taskAssigneeLike(SqlUtil.toLike(taskAssigneeLike));
		}
		String taskOwner = condition.getTaskOwner();
		if (StringUtil.isNotBlank(taskOwner)) {
			query.taskOwner(taskOwner);
		}
		String taskOwnerLike = condition.getTaskOwnerLike();
		if (StringUtil.isNotBlank(taskOwnerLike)) {
			query.taskOwnerLike(SqlUtil.toLike(taskOwnerLike));
		}
		String taskInvolvedUser = condition.getTaskInvolvedUser();
		if (StringUtil.isNotBlank(taskInvolvedUser)) {
			query.taskInvolvedUser(taskInvolvedUser);
		}
		Integer taskPriority = condition.getTaskPriority();
		if (taskPriority != null) {
			query.taskPriority(taskPriority);
		}
		Integer taskMinPriority = condition.getTaskMinPriority();
		if (taskMinPriority != null) {
			query.taskMinPriority(taskMinPriority);
		}
		Integer taskMaxPriority = condition.getTaskMaxPriority();
		if (taskMaxPriority != null) {
			query.taskMaxPriority(taskMaxPriority);
		}
		String parentTaskId = condition.getParentTaskId();
		if (StringUtil.isNotBlank(parentTaskId)) {
			query.taskParentTaskId(parentTaskId);
		}
		String dueDate = condition.getDueDate();
		if (StringUtil.isNotBlank(dueDate)) {
			query.taskDueDate(DateUtil.parseDatetime(dueDate));
		}
		String dueDateAfter = condition.getDueDateAfter();
		if (StringUtil.isNotBlank(dueDateAfter)) {
			query.taskDueAfter(DateUtil.parseDatetime(dueDateAfter));
		}
		String dueDateBefore = condition.getDueDateBefore();
		if (StringUtil.isNotBlank(dueDateBefore)) {
			query.taskDueBefore(DateUtil.parseDatetime(dueDateBefore));
		}
		String taskCreatedOn = condition.getTaskCreatedOn();
		if (StringUtil.isNotBlank(taskCreatedOn)) {
			query.taskCreatedOn(DateUtil.parseDatetime(taskCreatedOn));
		}
		String taskCreatedAfter = condition.getTaskCreatedAfter();
		if (StringUtil.isNotBlank(taskCreatedAfter)) {
			query.taskCreatedAfter(DateUtil.parseDatetime(taskCreatedAfter));
		}
		String taskCreatedBefore = condition.getTaskCreatedBefore();
		if (StringUtil.isNotBlank(taskCreatedBefore)) {
			query.taskCreatedBefore(DateUtil.parseDatetime(taskCreatedBefore));
		}
		String taskCompletedOn = condition.getTaskCompletedOn();
		if (StringUtil.isNotBlank(taskCompletedOn)) {
			query.taskCompletedOn(DateUtil.parseDatetime(taskCompletedOn));
		}
		String taskCompletedAfter = condition.getTaskCompletedAfter();
		if (StringUtil.isNotBlank(taskCompletedAfter)) {
			query.taskCompletedAfter(DateUtil.parseDatetime(taskCompletedAfter));
		}
		String taskCompletedBefore = condition.getTaskCompletedBefore();
		if (StringUtil.isNotBlank(taskCompletedBefore)) {
			query.taskCompletedBefore(DateUtil.parseDatetime(taskCompletedBefore));
		}
		String tenantId = condition.getTenantId();
		if (StringUtil.isNotBlank(tenantId)) {
			query.taskTenantId(tenantId);
		}
		String tenantIdLike = condition.getTenantIdLike();
		if (StringUtil.isNotBlank(tenantIdLike)) {
			query.taskTenantIdLike(SqlUtil.toLike(tenantIdLike));
		}
		Boolean finished = condition.getFinished();
		if (finished != null && finished) {
			query.finished();
		}
		Boolean processFinished = condition.getProcessFinished();
		if (processFinished != null && processFinished) {
			query.processFinished();
		}
		Boolean withoutDueDate = condition.getWithoutDueDate();
		if (withoutDueDate != null && withoutDueDate) {
			query.withoutTaskDueDate();
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

		List<VariableDto> processInstanceVariables = condition.getProcessVariables();
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
				} else if ("startTime".equals(pageSort)) {
				// TODO	query.orderByHistoricTaskInstanceStartTime(); 已过时
				} else if ("endTime".equals(pageSort)) {
					query.orderByHistoricTaskInstanceEndTime();
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

	private List<HistoricTaskInstanceProxy> toHistoricTaskInstanceProxyList(List<HistoricTaskInstance> historicTaskInstanceList) {
		List<HistoricTaskInstanceProxy> historicTaskInstanceProxyList = new ArrayList<>();

		for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
			historicTaskInstanceProxyList.add(toHistoricTaskInstanceProxy(historicTaskInstance));
		}

		return historicTaskInstanceProxyList;
	}

	private HistoricTaskInstanceProxy toHistoricTaskInstanceProxy(HistoricTaskInstance historicTaskInstance) {
		HistoricTaskInstanceProxy historicTaskInstanceProxy = new HistoricTaskInstanceProxy();
		BeanUtil.copy(historicTaskInstanceProxy, historicTaskInstance);

		Map<String, Object> variableMap = historicTaskInstance.getProcessVariables();
		List<VariableProxy> variableProxyList;
		if (variableMap == null) {
			variableProxyList = new ArrayList<>();
		} else {
			variableProxyList = ActivitiUtil.mapToList(variableMap);
		}
		historicTaskInstanceProxy.setVariables(variableProxyList);

		return historicTaskInstanceProxy;
	}
}
