package com.ritoinfo.framework.evo.sys.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-04-23 21:04
 */
@Data
public class PermissionDto implements Serializable {
	private String prefix;
	private String uri;
	private String method;
}
