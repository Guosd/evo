package com.ritoinfo.framework.evo.sp.sys.api;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.sys.dto.PermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-04 18:06
 */
@FeignClient(value = "evo-sp-sys", path = "/func")
public interface FuncApi {
	@GetMapping("/user/{username}")
	ServiceResponse<List<PermissionDto>> username(@PathVariable("username") String username);
}
