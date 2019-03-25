package com.github.framework.evo.flowable.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2019-03-25 10:39
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskInfoQueryCondition {
	private boolean runtimeQuery;
	private int firstResult;// 从0开始
	private int maxResults;
	private List<OrderQueryCondition> orders;//

	private String caseDefinitionId;
	private String caseInstanceId;
	private String caseInstanceIdWithChildren;
	private String cmmnDeploymentId;
	private List<String> cmmnDeploymentIds;
	private String deploymentId;
	private List<String> deploymentIds;
	private String executionId;
	private boolean ignoreAssigneeValue;
	private boolean includeIdentityLinks;
	private boolean includeProcessVariables;
	private boolean includeTaskLocalVariables;
	private Integer limitTaskVariables;
	private String locale;
	private String planItemInstanceId;
	private List<String> processCategoryInList;
	private List<String> processCategoryNotInList;
	private String processDefinitionId;
	private String processDefinitionKey;
	private List<String> processDefinitionKeys;
	private String processDefinitionKeyLike;
	private String processDefinitionKeyLikeIgnoreCase;
	private String processDefinitionName;
	private String processDefinitionNameLike;
	private String processInstanceBusinessKey;
	private String processInstanceBusinessKeyLike;
	private String processInstanceBusinessKeyLikeIgnoreCase;
	private String processInstanceId;
	private List<String> processInstanceIds;
	private String processInstanceIdWithChildren;
	private List<VariableQueryCondition> processVariableQueryConditions;//
	private String scopeDefinitionId;
	private String scopeId;
	private String scopeType;
	private String subScopeId;
	private String taskAssignee;
	private String taskAssigneeIds;
	private String taskAssigneeLike;
	private String taskAssigneeLikeIgnoreCase;
	private String taskCandidateGroup;
	private List<String> taskCandidateGroups;
	private String taskCandidateUser;
	private String taskCategory;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date taskCreatedAfter;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date taskCreatedBefore;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date taskCreatedOn;
	private String taskDefinitionId;
	private String taskDefinitionKey;
	private String taskDefinitionKeyLike;
	private String taskDescription;
	private String taskDescriptionLike;
	private String taskDescriptionLikeIgnoreCase;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date taskDueAfter;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date taskDueBefore;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date taskDueDate;
	private String taskId;
	private Set<String> taskInvolvedGroups;
	private String taskInvolvedUser;
	private Integer taskMaxPriority;
	private Integer taskMinPriority;
	private String taskName;
	private List<String> taskNames;
	private List<String> taskNameInIgnoreCases;
	private String taskNameLike;
	private String taskNameLikeIgnoreCase;
	private String taskOwner;
	private String taskOwnerLike;
	private String taskOwnerLikeIgnoreCase;
	private Integer taskPriority;
	private String taskTenantId;
	private String taskTenantIdLike;
	private List<VariableQueryCondition> taskVariableQueryConditions;//
	private boolean taskWithFormKey;
	private boolean taskWithoutTenantId;
	private boolean withLocalizationFallback;
	private boolean withoutTaskDueDate;
}
