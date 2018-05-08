package com.ritoinfo.framework.evo.sp.sys.crtl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Kyll
 * Date: 2018-05-08 11:31
 */
@RequestMapping("/menu")
@Controller
public class MenuCtrl {
	@GetMapping("/list")
	public String list() {
		return "/view/menu/list";
	}

	@GetMapping("/form")
	public String form() {
		return "/view/menu/form";
	}
}
