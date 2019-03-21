package com.github.framework.evo.sys.rest;

import com.github.framework.evo.base.rest.BaseRest;
import com.github.framework.evo.common.validate.group.CreateGroup;
import com.github.framework.evo.common.validate.group.UpdateGroup;
import com.github.framework.evo.sys.bizz.UserBizz;
import com.github.framework.evo.sys.condition.UserCondition;
import com.github.framework.evo.sys.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-02-13 13:43
 */
@Slf4j
@RequestMapping("user")
@RestController
public class UserRest extends BaseRest<UserBizz, Long, UserDto, UserCondition> {
	@GetMapping("/id/{id}/role")
	public UserDto getWithRole(@PathVariable Long id) {
		return bizz.getWithRole(id);
	}

	@GetMapping("/username/{username}")
	public UserDto getByUsername(@PathVariable String username) {
		return bizz.getByUsername(username);
	}

	@GetMapping("/mobile-number/{mobileNumber}")
	public UserDto getByMobileNumber(@PathVariable String mobileNumber) {
		return bizz.getByMobileNumber(mobileNumber);
	}

	@PostMapping("/all")
	public Long createAll(@Validated(CreateGroup.class) @RequestBody UserDto dto) {
		return bizz.createAll(dto);
	}

	@PutMapping("/with-role")
	public void updateWithRole(@Validated(CreateGroup.class) @RequestBody UserDto dto) {
		bizz.updateWithRole(dto);
	}

	@PutMapping("/password")
	public void updatePassword(@Validated(UpdateGroup.class) @RequestBody UserDto dto) {
		bizz.updatePassowrd(dto);
	}

	@PutMapping("/login-info/{id}")
	public void updateLoginInfo(@PathVariable("id") Long id, @RequestBody String loginIp) {
		bizz.updateLoginInfo(id, loginIp);
	}
}
