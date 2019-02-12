package com.ritoinfo.framework.evo.sys.crtl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Kyll
 * Date: 2018-05-04 08:57
 */
@RequestMapping("/micro")
@Controller
public class MicroCtrl {
	@GetMapping("/list")
	public String list() {
		return "view/micro/list";
	}

	@GetMapping("/form")
	public String form() {
		return "view/micro/form";
	}
}
