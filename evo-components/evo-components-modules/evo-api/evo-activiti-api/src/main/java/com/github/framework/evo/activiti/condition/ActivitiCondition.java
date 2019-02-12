package com.github.framework.evo.activiti.condition;


import com.github.framework.evo.activiti.dto.VariableDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * User: Kyll
 * Date: 2016-06-27 11:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivitiCondition extends ActivitiPageCondition {
	// 任务是否完成（已办true or待办false）
	private boolean taskComplete;

	// 公共查询条件
	private String id;
	private String name;
	private String nameLike;
	// 流程定义查询条件
	private Integer version;
	private String key;
	private String keyLike;
	private String resourceName;
	private String resourceNameLike;
	private String category;
	private String categoryLike;
	private String categoryNotEquals;
	private String deploymentId;
	private String startableByUser;
	private Boolean latest;
	private Boolean suspended;
	// 公共任务查询条件
	private String taskDefinitionKey;
	private String taskDefinitionKeyLike;
	private String processInstanceId;
	private String processDefinitionKey;
	private String processDefinitionKeyLike;
	private String processDefinitionName;
	private String processDefinitionNameLike;
	private String executionId;
	private Boolean withoutDueDate;
	private Boolean includeTaskLocalVariables;
	private Boolean includeProcessVariables;
	private String tenantId;
	private String tenantIdLike;
	private Boolean withoutTenantId;
	private List<VariableDto> taskVariables;
	// 待办任务查询条件
	private String description;
	private Integer priority;
	private Integer minimumPriority;
	private Integer maximumPriority;
	private String assignee;
	private String assigneeLike;
	private String owner;
	private String ownerLike;
	private Boolean unassigned;
	private String delegationState;
	private String candidateUser;
	private String candidateGroup;
	private String candidateGroups;
	private String involvedUser;
	private String processInstanceBusinessKey;
	private String processInstanceBusinessKeyLike;
	private String createdOn;
	private String createdBefore;
	private String createdAfter;
	private String dueOn;
	private String dueBefore;
	private String dueAfter;
	private Boolean excludeSubTasks;
	private Boolean active;
	private String candidateOrAssigned;
	private List<VariableDto> processInstanceVariables;
	// 已办任务查询条件
	private String taskId;
	private String processBusinessKey;
	private String processBusinessKeyLike;
	private String processDefinitionId;
	private String taskName;
	private String taskNameLike;
	private String taskDescription;
	private String taskDescriptionLike;
	private String taskDeleteReason;
	private String taskDeleteReasonLike;
	private String taskAssignee;
	private String taskAssigneeLike;
	private String taskOwner;
	private String taskOwnerLike;
	private String taskInvolvedUser;
	private Integer taskPriority;
	private Integer taskMinPriority;
	private Integer taskMaxPriority;
	private Boolean finished;
	private Boolean processFinished;
	private String parentTaskId;
	private String dueDate;
	private String dueDateAfter;
	private String dueDateBefore;
	private String taskCreatedOn;
	private String taskCreatedBefore;
	private String taskCreatedAfter;
	private String taskCompletedOn;
	private String taskCompletedBefore;
	private String taskCompletedAfter;
	private List<VariableDto> processVariables;
	// 历史变量查询条件
	private Boolean excludeTaskVariables;
	private String variableName;
	private String variableNameLike;
}
