package com.github.framework.evo.activiti.dto;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2016-06-27 17:25
 */
@Data
public class VariableDto {
	private String name;
	private String type;
	private Object value;
	private String scope;
	private String operation;
	private String valueUrl;
}
