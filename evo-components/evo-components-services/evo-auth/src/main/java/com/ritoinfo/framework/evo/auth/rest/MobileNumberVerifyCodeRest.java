package com.ritoinfo.framework.evo.auth.rest;

import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.exception.BizzException;
import com.ritoinfo.framework.evo.common.exception.RestException;
import com.ritoinfo.framework.evo.common.uitl.HttpServletUtil;
import com.ritoinfo.framework.evo.auth.bizz.MobileNumberVerifyCodeBizz;
import com.ritoinfo.framework.evo.auth.exception.VerifyCodeInvalidException;
import com.ritoinfo.framework.evo.auth.exception.VerifyCodeSendException;
import com.ritoinfo.framework.evo.auth.model.MobileNumberParam;
import com.ritoinfo.framework.evo.auth.model.VerifyCodeDto;
import com.ritoinfo.framework.evo.auth.model.VerifyCodeParam;
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
	public ServiceResponse<VerifyCodeDto> get(@Validated @RequestBody VerifyCodeParam verifyCodeParam) {
		try {
			return ServiceResponse.ok(mobileNumberVerifyCodeBizz.get(verifyCodeParam));
		} catch (VerifyCodeSendException e) {
			throw new RestException(Const.RC_AUTH_M_VERIFY_CODE_SEND, e);
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_M_VERIFY_CODE, e);
		}
	}

	@PostMapping("/verify-code/sign-in")
	public ServiceResponse<String> getForSignIn(@Validated @RequestBody VerifyCodeParam verifyCodeParam) {
		try {
			return ServiceResponse.ok(mobileNumberVerifyCodeBizz.getForSignIn(verifyCodeParam));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_M_VERIFY_CODE_SIGN_IN, e);
		}
	}

	@PostMapping("/verify-code/sign-up")
	public ServiceResponse<String> getForSignUp(@Validated @RequestBody VerifyCodeParam verifyCodeParam) {
		try {
			return ServiceResponse.ok(mobileNumberVerifyCodeBizz.getForSignUp(verifyCodeParam));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_M_VERIFY_CODE_SIGN_UP, e);
		}
	}

	@PostMapping("/login")
	public ServiceResponse<String> login(@Validated @RequestBody MobileNumberParam mobileNumberParam, HttpServletRequest request) {
		try {
			return ServiceResponse.ok(mobileNumberVerifyCodeBizz.login(mobileNumberParam, HttpServletUtil.extractRemoteAddr(request)));
		} catch (VerifyCodeInvalidException e) {
			throw new RestException(Const.RC_AUTH_M_LOGIN_VERIFY_CODE, e);
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_M_LOGIN, e);
		}
	}
}
