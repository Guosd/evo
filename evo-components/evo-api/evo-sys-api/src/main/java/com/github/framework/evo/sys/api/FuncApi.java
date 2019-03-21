package com.github.framework.evo.sys.api;

import com.github.framework.evo.sys.dto.PermissionDto;
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
	List<PermissionDto> findByUsername(@PathVariable("username") String username);
}
