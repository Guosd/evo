package com.ritoinfo.framework.evo.zuul.routelocator.rest;

import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.zuul.routelocator.bizz.EvoZuulRouteBizz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-08-21 11:47
 */
@Slf4j
@RequestMapping("/route")
@RestController
public class EvoZuulRouteRest {
	@Autowired
	private EvoZuulRouteBizz evoZuulRouteBizz;

	@PostMapping("/refresh")
	public ServiceResponse refresh() {
		evoZuulRouteBizz.refresh();
		return ServiceResponse.ok();
	}
}
