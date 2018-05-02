package com.ritoinfo.framework.evo.sp.sys.api;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * User: Kyll
 * Date: 2018-02-13 13:43
 */
@FeignClient(value = "evo-sp-sys", path = "/user")
public interface UserApi {
	@GetMapping("/username/{username}")
	ServiceResponse<UserDto> username(@PathVariable("username") String username);

	@GetMapping("/mobile-number/{mobileNumber}")
	ServiceResponse<UserDto> mobileNumber(@PathVariable("mobileNumber") String mobileNumber);

	@PutMapping
	ServiceResponse update(@RequestBody UserDto dto, @RequestHeader(Const.JWT_TOKEN_HEADER) String token);
}
