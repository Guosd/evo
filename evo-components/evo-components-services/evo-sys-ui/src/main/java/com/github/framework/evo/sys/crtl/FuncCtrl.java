package com.github.framework.evo.sys.crtl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Kyll
 * Date: 2018-05-08 11:30
 */
@RequestMapping("/func")
@Controller
public class FuncCtrl {
	@GetMapping("/list")
	public String list() {
		return "view/func/list";
	}

	@GetMapping("/form")
	public String form() {
		return "view/func/form";
	}
}
