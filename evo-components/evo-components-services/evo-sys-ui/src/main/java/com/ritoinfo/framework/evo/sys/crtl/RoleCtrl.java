package com.ritoinfo.framework.evo.sys.crtl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Kyll
 * Date: 2018-05-08 11:31
 */
@RequestMapping("/role")
@Controller
public class RoleCtrl {
	@GetMapping("/list")
	public String list() {
		return "view/role/list";
	}

	@GetMapping("/form")
	public String form() {
		return "view/role/form";
	}
}
