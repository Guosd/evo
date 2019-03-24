package com.github.framework.evo.flowable.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-03-24 12:22
 */
@Data
public class ProcessInstanceDto {
	private String processDefinitionId;
	private String processDefinitionName;
	private String processDefinitionKey;
	private Integer processDefinitionVersion;
	private String deploymentId;
	private String businessKey;
	private boolean suspended;
	private Map<String, Object> processVariables;
	private String tenantId;
	private String name;
	private String description;
	private String localizedName;
	private String localizedDescription;
	private Date startTime;
	private String startUserId;
	private String callbackId;
	private String callbackType;
}
