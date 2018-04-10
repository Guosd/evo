package com.ritoinfo.framework.evo.activiti.proxy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User: Kyll
 * Date: 2016-06-27 17:42
 */
@Data
public class HistoricTaskInstanceProxy implements Serializable {
	private String id;
	private String processDefinitionId;
	private String processDefinitionUrl;
	private String processInstanceId;
	private String processInstanceUrl;
	private String executionId;
	private String name;
	private String description;
	private String deleteReason;
	private String owner;
	private String assignee;
	private Date startTime;
	private Date endTime;
	private Long durationInMillis;
	private Long workTimeInMillis;
	private Date claimTime;
	private String taskDefinitionKey;
	private String formKey;
	private Integer priority;
	private Date dueDate;
	private String parentTaskId;
	private String url;
	private String tenantId;
	private String category;
	private List<VariableProxy> variables;
}
