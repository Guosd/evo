package com.ritoinfo.framework.evo.activiti.dto;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2016-07-09 11:56
 */
@Data
public class ProcessDefinitionDto {
	private String id;
	private String url;
	private String key;
	private Integer version;
	private String name;
	private String description;
	private String tenantId;
	private String deploymentId;
	private String deploymentUrl;
	private String resource;
	private String diagramResource;
	private String category;
	private Boolean graphicalNotationDefined;
	private Boolean suspended;
	private Boolean startFormDefined;
}
