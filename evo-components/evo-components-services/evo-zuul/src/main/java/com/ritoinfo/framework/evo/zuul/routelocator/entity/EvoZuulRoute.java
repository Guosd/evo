package com.ritoinfo.framework.evo.zuul.routelocator.entity;

import com.ritoinfo.framework.evo.base.starter.entity.BaseMapperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * User: Kyll
 * Date: 2018-08-21 09:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "zuul_route", catalog = "evo_framework")
public class EvoZuulRoute extends BaseMapperEntity<Long> {
	private Long id;
	private String routeId;
	private String path;
	private String serviceId;
	private String url;
	private String stripPrefix;
	private String retryable;
	private String sensitiveHeaders;
	private String enabled;
	private String comment;
}
