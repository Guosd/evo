package com.ritoinfo.framework.evo.sp.activiti.proxy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-02 09:44
 */
@Data
public class ProcessInstanceProxy implements Serializable {
	private String id;
	private String url;
	private String businessKey;
	private Boolean suspended;
	private Boolean ended;
	private String processDefinitionId;
	private String processDefinitionUrl;
	private String activityId;
	private String tenantId;
	private Boolean completed;
	private List<VariableProxy> variables;
}
