package com.ritoinfo.framework.evo.activiti.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * User: Kyll
 * Date: 2016-07-09 11:59
 */
@Data
public class ProcessInstanceDto {
	// Common
	private String id;
	private String url;
	private String businessKey;
	private String processDefinitionId;
	private String processDefinitionUrl;
	private String tenantId;
	private List<VariableDto> variableDtoList;
	// ProcessInstance
	private Boolean suspended;
	private Boolean ended;
	private String activityId;
	private Boolean completed;
	// HistoricProcessInstance
	private Date startTime;
	private Date endTime;
	private Long durationInMillis;
	private String startUserId;
	private String startActivityId;
	private String endActivityId;
	private String deleteReason;
	private String superProcessInstanceId;
}
