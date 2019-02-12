package com.ritoinfo.framework.evo.sys.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-04-26 08:22
 */
@Data
public class MyMenuDto implements Serializable {
	private Long id;
	private Long parentId;
	private String name;
	private String code;
	private String prefix;
	private String uri;
	private String method;
}
