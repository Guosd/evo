package com.github.framework.evo.sys.api;

import com.github.framework.evo.common.Const;
import com.github.framework.evo.sys.dto.UserDto;
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
@FeignClient(value = "evo-sys", path = "/user")
public interface UserApi {
	@GetMapping("/{id}")
	UserDto get(@PathVariable("id") Long id);

	@GetMapping("/username/{username}")
	UserDto getByUsername(@PathVariable("username") String username);

	@GetMapping("/mobile-number/{mobileNumber}")
	UserDto getByMobileNumber(@PathVariable("mobileNumber") String mobileNumber);

	@PutMapping
	void update(@RequestBody UserDto dto, @RequestHeader(Const.HTTP_HEADER_TOKEN) String token);
}
