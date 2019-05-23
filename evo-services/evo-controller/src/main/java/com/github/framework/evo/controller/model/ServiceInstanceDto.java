package com.github.framework.evo.controller.model;

import lombok.Data;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-04-15 16:55
 */
@Data
public class ServiceInstanceDto {
	private String name;
	private String instanceId;
	private String hostName;
	private String app;
	private String ipAddr;
	private String status;
	private String overriddenStatus;
	private Integer port;
	private Boolean portEnabled;
	private Integer securePort;
	private Boolean securePortEnabled;
	private String countryId;
	private Integer renewalIntervalInSecs;
	private Integer durationInSecs;
	private Long registrationTimestamp;
	private Long lastRenewalTimestamp;
	private Long evictionTimestamp;
	private Long serviceUpTimestamp;
	private Map<String, String> metadata;
	private String homePageUrl;
	private String statusPageUrl;
	private String healthCheckUrl;
	private String vipAddress;
	private String secureVipAddress;
	private Boolean coordinatingDiscoveryServer;
	private Long lastUpdatedTimestamp;
	private Long lastDirtyTimestamp;
	private String actionType;
}
