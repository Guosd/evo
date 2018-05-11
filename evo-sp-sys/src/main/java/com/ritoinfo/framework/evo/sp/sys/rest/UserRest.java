package com.ritoinfo.framework.evo.sp.sys.rest;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.CreateGroup;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.UpdateGroup;
import com.ritoinfo.framework.evo.sp.sys.bizz.UserBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.UserDao;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import com.ritoinfo.framework.evo.sp.sys.entity.User;
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
public class UserRest extends BaseRest<UserBizz, UserDao, User, Long, UserCondition, UserDto> {
	@GetMapping("/id/{id}/role")
	public ServiceResponse<UserDto> withRole(@PathVariable Long id) {
		return ServiceResponse.ok(bizz.getWithRole(id));
	}

	@GetMapping("/username/{username}")
	public ServiceResponse<UserDto> username(@PathVariable String username) {
		return ServiceResponse.ok(bizz.getByUsername(username));
	}

	@GetMapping("/mobile-number/{mobileNumber}")
	public ServiceResponse<UserDto> mobileNumber(@PathVariable String mobileNumber) {
		return ServiceResponse.ok(bizz.getByMobileNumber(mobileNumber));
	}

	// TODO 兼容 SCFW
	@GetMapping("/user-context")
	public ServiceResponse<UserDto> userContext() {
		return ServiceResponse.ok(bizz.getUserContext());
	}

	@PostMapping("/all")
	public ServiceResponse<Long> createAll(@Validated(CreateGroup.class) @RequestBody UserDto dto) {
		return ServiceResponse.ok(bizz.createAll(dto));
	}

	@PutMapping("/with-role")
	public ServiceResponse<Long> updateWithRole(@Validated(CreateGroup.class) @RequestBody UserDto dto) {
		bizz.updateWithRole(dto);
		return ServiceResponse.ok();
	}

	@PutMapping("/password")
	public ServiceResponse updatePassword(@Validated(UpdateGroup.class) @RequestBody UserDto dto) {
		bizz.updatePassowrd(dto);
		return ServiceResponse.ok();
	}
}
