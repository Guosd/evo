package com.github.framework.evo.auth.rest;

import com.github.framework.evo.auth.bizz.UsernamePasswordBizz;
import com.github.framework.evo.auth.model.UsernamePasswordParam;
import com.github.framework.evo.common.uitl.HttpServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2018-04-21 16:42
 */
@Slf4j
@RequestMapping("/username-password")
@RestController
public class UsernamePasswordRest {
	@Autowired
	private UsernamePasswordBizz usernamePasswordBizz;

	@PostMapping("/login")
	public String login(@Validated @RequestBody UsernamePasswordParam usernamePasswordParam, HttpServletRequest request) {
		return usernamePasswordBizz.login(usernamePasswordParam, HttpServletUtil.extractRemoteAddr(request));
	}
}
