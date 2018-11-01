package com.ritoinfo.framework.evo.dts.server.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Kyll
 * Date: 2018-10-31 14:33
 */
@RequestMapping("/record")
@Controller
public class DtsRecordCtrl {
	@GetMapping("/list")
	public String list() {
		return "view/record/list";
	}

	@GetMapping("/form")
	public String form() {
		return "view/record/form";
	}
}
