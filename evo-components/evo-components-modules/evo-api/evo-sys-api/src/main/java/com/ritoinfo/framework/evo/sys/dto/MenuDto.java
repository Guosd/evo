package com.ritoinfo.framework.evo.sys.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-04-23 21:11
 */
@Data
public class MenuDto implements Serializable {
	private Long id;
	private Long parentId;
	private String parentName;
	private Long funcId;
	private String funcName;
	private String funcCode;
	private String funcUri;
	private String funcMethod;
	private String name;
	private String code;
	private Integer sort;
}
