package com.github.framework.evo.sys.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-03-08 11:43
 */
@Data
public class FuncDto implements Serializable {
	private Long id;
	private Long microId;
	private String microName;
	private String name;
	private String code;
	private String uri;
	private String method;
}
