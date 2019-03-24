package com.github.framework.evo.flowable.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

/**
 * User: Kyll
 * Date: 2019-03-24 11:10
 */
@Data
public class ProcessDefinitionDto {
	private String id;
	private String category;
	private String name;
	private String key;
	private String description;
	private int version;
	private String resourceName;
	private String deploymentId;
	private String diagramResourceName;
	@Getter(AccessLevel.NONE) private boolean startFormKey;
	@Getter(AccessLevel.NONE) private boolean graphicalNotation;
	private boolean suspended;
	private String tenantId;
	private String derivedFrom;
	private String derivedFromRoot;
	private int derivedVersion;
	private String engineVersion;

	public boolean hasStartFormKey() {
		return this.startFormKey;
	}

	public boolean hasGraphicalNotation() {
		return this.graphicalNotation;
	}
}
