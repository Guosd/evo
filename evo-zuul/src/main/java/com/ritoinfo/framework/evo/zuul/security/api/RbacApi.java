package com.ritoinfo.framework.evo.zuul.security.api;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.zuul.security.api.model.RbacDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-12-28 18:20
 */
@FeignClient("${evo.sp.auth.rbac-service-id}")
public interface RbacApi {
	@PostMapping("${evo.sp.auth.rbac-uri}")
	ServiceResponse<Boolean> validate(@RequestBody RbacDto rbacDto);
}
