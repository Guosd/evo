package com.github.framework.evo.zuul.dynamicroute.rest;

import com.github.framework.evo.zuul.dynamicroute.bizz.EvoZuulRouteBizz;
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
	public void refresh() {
		evoZuulRouteBizz.refresh();
	}
}
