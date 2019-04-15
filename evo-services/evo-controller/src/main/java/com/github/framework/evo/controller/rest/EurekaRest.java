package com.github.framework.evo.controller.rest;

import com.github.framework.evo.controller.bizz.EurekaBizz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2019-04-15 16:11
 */
@Slf4j
@RequestMapping("/eureka")
@RestController
public class EurekaRest {
	@Autowired
	private EurekaBizz eurekaBizz;

	@PostMapping("/list")
	public void list() {
		eurekaBizz.list();
	}
}
