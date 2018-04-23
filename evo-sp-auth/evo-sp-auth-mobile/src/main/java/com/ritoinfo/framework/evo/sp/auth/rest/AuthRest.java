package com.ritoinfo.framework.evo.sp.auth.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.sp.auth.bizz.AuthBizz;
import com.ritoinfo.framework.evo.sp.auth.dto.MobileCodeDto;
import com.ritoinfo.framework.evo.sp.auth.dto.MobileLoginDto;
import com.ritoinfo.framework.evo.sp.base.exception.BizzException;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2018-04-20 11:47
 */
@Slf4j
@RestController
public class AuthRest {
	@Autowired
	private AuthBizz authBizz;

	@PostMapping("code")
	public ServiceResponse<String> code(@Validated @RequestBody MobileCodeDto mobileCodeDto) {
		try {
			return ServiceResponse.ok(authBizz.generateCode(mobileCodeDto));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_M_VERIFY_CODE, e);
		}
	}

	@PostMapping("login")
	public ServiceResponse<String> login(@Validated @RequestBody MobileLoginDto loginDto, HttpServletRequest request) {
		try {
			return ServiceResponse.ok(authBizz.authorize(loginDto, request));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_M_LOGIN, e);
		}
	}
}