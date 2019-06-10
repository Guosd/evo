package com.github.framework.evo.controller.model;

import com.github.framework.evo.common.model.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2019-05-24 14:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigItemQuery extends PageDto {
	private String label;
	private String application;
	private String profile;
	private String key;
	private String value;
	private String comment;
}
