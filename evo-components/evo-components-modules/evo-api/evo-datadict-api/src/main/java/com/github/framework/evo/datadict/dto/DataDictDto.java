package com.github.framework.evo.datadict.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-04-13 09:53
 */
@Data
public class DataDictDto implements Serializable {
	private Long id;
	private String name;
	private String code;
	private String key;
	private String value;
	private Integer sort;
}
