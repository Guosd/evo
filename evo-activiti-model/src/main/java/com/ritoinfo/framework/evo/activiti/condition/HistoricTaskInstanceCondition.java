package com.ritoinfo.framework.evo.activiti.condition;

import com.ritoinfo.framework.evo.activiti.dto.VariableDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-09 11:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HistoricTaskInstanceCondition extends ActivitiPageCondition {
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
}
