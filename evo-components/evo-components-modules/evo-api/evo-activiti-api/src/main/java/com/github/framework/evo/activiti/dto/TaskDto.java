package com.github.framework.evo.activiti.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * User: Kyll
 * Date: 2016-07-09 11:16
 */
@Data
public class TaskDto {
	// Common
	private String id;
	private String url;
	private String owner;
	private String assignee;
	private String name;
	private String description;
	private Date dueDate;
	private Integer priority;
	private String taskDefinitionKey;
	private String tenantId;
	private String category;
	private String formKey;
	private String parentTaskId;
	private String executionId;
	private ProcessInstanceDto processInstanceDto;
	private ProcessDefinitionDto processDefinitionDto;
	private List<VariableDto> variableDtoList;
	// Task
	private String delegationState;
	private Date createTime;
	private Boolean suspended;
	private String parentTaskUrl;
	private String executionUrl;
	// HistoricTask
	private String deleteReason;
	private Date startTime;
	private Date endTime;
	private Long durationInMillis;
	private Long workTimeInMillis;
	private Date claimTime;
	// Extra Variable
	private String defpvPrevOutgoing;
	private String pvActorId;
	private String pvActorIdText;
	private String pvActorRoles;
	private String pvActorRolesText;
}
