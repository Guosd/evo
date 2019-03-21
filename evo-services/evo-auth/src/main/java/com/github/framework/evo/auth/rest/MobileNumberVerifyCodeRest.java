package com.github.framework.evo.auth.rest;

import com.github.framework.evo.auth.bizz.MobileNumberVerifyCodeBizz;
import com.github.framework.evo.auth.model.MobileNumberParam;
import com.github.framework.evo.auth.model.VerifyCodeDto;
import com.github.framework.evo.auth.model.VerifyCodeParam;
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
 * Date: 2018-04-20 11:47
 */
@Slf4j
@RequestMapping("/mobile-number")
@RestController
public class MobileNumberVerifyCodeRest {
	@Autowired
	private MobileNumberVerifyCodeBizz mobileNumberVerifyCodeBizz;

	@PostMapping("/verify-code")
	public VerifyCodeDto get(@Validated @RequestBody VerifyCodeParam verifyCodeParam) {
		return mobileNumberVerifyCodeBizz.get(verifyCodeParam);
	}

	@PostMapping("/verify-code/sign-in")
	public String getForSignIn(@Validated @RequestBody VerifyCodeParam verifyCodeParam) {
		return mobileNumberVerifyCodeBizz.getForSignIn(verifyCodeParam);
	}

	@PostMapping("/verify-code/sign-up")
	public String getForSignUp(@Validated @RequestBody VerifyCodeParam verifyCodeParam) {
		return mobileNumberVerifyCodeBizz.getForSignUp(verifyCodeParam);
	}

	@PostMapping("/login")
	public String login(@Validated @RequestBody MobileNumberParam mobileNumberParam, HttpServletRequest request) {
		return mobileNumberVerifyCodeBizz.login(mobileNumberParam, HttpServletUtil.extractRemoteAddr(request));
	}
}
