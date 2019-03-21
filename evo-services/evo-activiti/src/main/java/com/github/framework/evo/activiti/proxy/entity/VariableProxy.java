package com.github.framework.evo.activiti.proxy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-04-02 09:42
 */
@Data
public class VariableProxy implements Serializable {
	private String name;
	private String type;
	private Object value;
	private String scope;
	private String operation;
	private String valueUrl;

	public VariableProxy() {
	}

	public VariableProxy(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public VariableProxy(String name, String type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}
}
