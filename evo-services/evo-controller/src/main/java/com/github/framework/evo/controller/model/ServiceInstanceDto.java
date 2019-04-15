package com.github.framework.evo.controller.model;

import lombok.Data;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-04-15 16:55
 */
@Data
public class ServiceInstanceDto {
	private String instanceId;
	private String serviceId;
	private String host;
	private int port;
	private String uri;
	private boolean secure;
	private String scheme;
	private Map<String, String> metadata;

	private String discoveryServerType;
}
