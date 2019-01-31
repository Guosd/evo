package com.ritoinfo.framework.evo.sp.auth.authorization.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.PermissionBizz;
import com.ritoinfo.framework.evo.sp.auth.model.PermissionParam;
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

/**
 * User: Kyll
 * Date: 2019-01-16 13:43
 */
@Slf4j
@RequestMapping("/permission")
@RestController
public class PermissionRest {
	@Autowired
	private PermissionBizz permissionBizz;

	@PostMapping("/check")
	public ServiceResponse<Boolean> check(@Validated @RequestBody PermissionParam permissionParam) {
		try {
			return ServiceResponse.ok(permissionBizz.check(permissionParam));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_VERIFY, e);
		}
	}
}
