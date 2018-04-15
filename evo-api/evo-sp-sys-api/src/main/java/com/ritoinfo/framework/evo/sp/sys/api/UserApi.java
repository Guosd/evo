package com.ritoinfo.framework.evo.sp.sys.api;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * User: Kyll
 * Date: 2018-02-13 13:43
 */
@Component
@FeignClient(value = "evo-sp-sys", path = "/user")
public interface UserApi {
	@GetMapping("/username/{username}")
	ServiceResponse<UserDto> username(@PathVariable("username") String username);
}
