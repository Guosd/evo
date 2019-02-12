package com.github.framework.evo.activiti.proxy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2016-06-27 13:07
 */
@Data
public class ProcessDefinitionProxy implements Serializable {
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
