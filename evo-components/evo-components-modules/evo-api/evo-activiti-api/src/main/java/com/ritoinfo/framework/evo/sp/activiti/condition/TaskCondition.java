package com.ritoinfo.framework.evo.sp.activiti.condition;

import com.ritoinfo.framework.evo.sp.activiti.dto.VariableDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-09 13:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskCondition extends ActivitiPageCondition {
	private String name;
	private String nameLike;

	private String taskId;
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
}
