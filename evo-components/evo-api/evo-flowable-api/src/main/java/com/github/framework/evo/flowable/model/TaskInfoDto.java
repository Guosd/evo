package com.github.framework.evo.flowable.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-03-25 10:30
 */
@Data
public class TaskInfoDto {
	private String id;
	private String name;
	private String description;
	private int priority;
	private String owner;
	private String assignee;
	private String processInstanceId;
	private String executionId;
	private String taskDefinitionId;
	private String processDefinitionId;
	private String scopeId;
	private String subScopeId;
	private String scopeType;
	private String scopeDefinitionId;
	private Date createTime;
	private String taskDefinitionKey;
	private Date dueDate;
	private String category;
	private String parentTaskId;
	private String tenantId;
	private String formKey;
	private Map<String, Object> taskLocalVariables;
	private Map<String, Object> processVariables;
//	private List<? extends IdentityLinkInfo> getIdentityLinks;
	private Date claimTime;
}
