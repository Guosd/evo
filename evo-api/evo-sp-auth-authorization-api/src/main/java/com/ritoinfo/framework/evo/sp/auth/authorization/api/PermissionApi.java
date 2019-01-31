package com.ritoinfo.framework.evo.sp.auth.authorization.api;

import com.ritoinfo.framework.evo.sp.auth.model.PermissionParam;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2019-01-16 13:43
 */
@FeignClient(name = "evo-sp-auth-authorization", path = "/permission")
public interface PermissionApi {
	@PostMapping("/check")
	ServiceResponse<Boolean> check(@Validated @RequestBody PermissionParam permissionParam);
}
