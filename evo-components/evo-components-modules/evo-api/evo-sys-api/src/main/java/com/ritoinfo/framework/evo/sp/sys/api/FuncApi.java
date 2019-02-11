package com.ritoinfo.framework.evo.sp.sys.api;

import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.sys.dto.PermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-04 18:06
 */
@FeignClient(value = "evo-sys", path = "/func")
public interface FuncApi {
	@GetMapping("/user/username/{username}")
	ServiceResponse<List<PermissionDto>> getByUsername(@PathVariable("username") String username);
}
