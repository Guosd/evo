package com.ritoinfo.framework.evo.sp.dts.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Kyll
 * Date: 2018-10-31 14:34
 */
@RequestMapping("/message")
@Controller
public class DtsMessageCtrl {
	@GetMapping("/list")
	public String list() {
		return "view/message/list";
	}

	@GetMapping("/form")
	public String form() {
		return "view/message/form";
	}
}
