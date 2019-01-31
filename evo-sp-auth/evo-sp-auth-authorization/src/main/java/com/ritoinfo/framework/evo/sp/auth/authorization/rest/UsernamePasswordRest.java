package com.ritoinfo.framework.evo.sp.auth.authorization.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.NetUtil;
import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.UsernamePasswordBizz;
import com.ritoinfo.framework.evo.sp.auth.model.UsernamePasswordParam;
import com.ritoinfo.framework.evo.sp.base.exception.BizzException;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
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
	public ServiceResponse<String> login(@Validated @RequestBody UsernamePasswordParam usernamePasswordParam, HttpServletRequest request) {
		try {
			return ServiceResponse.ok(usernamePasswordBizz.login(usernamePasswordParam, NetUtil.extractRemoteAddr(request)));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_LOGIN, e);
		}
	}
}
