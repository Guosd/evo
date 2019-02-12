package com.github.framework.evo.sys.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-04-23 19:37
 */
@Data
public class MicroDto implements Serializable {
	private Long id;
	private String name;
	private String code;
	private String prefix;
}
