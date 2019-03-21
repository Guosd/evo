package com.github.framework.evo.sys.condition;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2019-01-02 14:48
 */
@Data
public class PermissionCondition implements Serializable {
	private String userId;
	private String uri;
	private String method;
}
