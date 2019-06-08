package com.github.framework.evo.controller.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2019-06-09 02:33
 */
@Data
public class ConfigPropertyDto {
	private Long id;
	private String label;
	private String application;
	private String profile;
	private String key;
	@NotBlank
	private String value;
	private String comment;
}
