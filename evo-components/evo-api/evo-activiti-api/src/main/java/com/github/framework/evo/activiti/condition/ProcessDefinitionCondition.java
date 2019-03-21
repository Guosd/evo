package com.github.framework.evo.activiti.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-09 09:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProcessDefinitionCondition extends ActivitiPageCondition {
	private String name;
	private String nameLike;

	private Integer version;
	private String key;
	private String keyLike;
	private String resourceName;
	private String resourceNameLike;
	private String category;
	private String categoryLike;
	private String categoryNotEquals;
	private String deploymentId;
	private String startableByUser;
	private Boolean latest;
	private Boolean suspended;
}
