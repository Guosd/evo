package com.ritoinfo.framework.evo.sp.activiti.proxy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User: Kyll
 * Date: 2016-06-27 14:44
 */
@Data
public class TaskProxy implements Serializable {
	private String id;
	private String url;
	private String owner;
	private String assignee;
	private String delegationState;
	private String name;
	private String description;
	private Date createTime;
	private Date dueDate;
	private Integer priority;
	private Boolean suspended;
	private String taskDefinitionKey;
	private String tenantId;
	private String category;
	private String formKey;
	private String parentTaskId;
	private String parentTaskUrl;
	private String executionId;
	private String executionUrl;
	private String processInstanceId;
	private String processInstanceUrl;
	private String processDefinitionId;
	private String processDefinitionUrl;
	private List<VariableProxy> variables;
}
