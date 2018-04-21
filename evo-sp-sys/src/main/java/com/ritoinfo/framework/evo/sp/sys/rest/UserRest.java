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
	@GetMapping("/username/{username}")
	public ServiceResponse<UserDto> username(@PathVariable String username) {
		return ServiceResponse.ok(bizz.getByUsername(username));
	}

	@GetMapping("/mobile-number/{mobileNumber}")
	public ServiceResponse<UserDto> mobileNumber(@PathVariable String mobileNumber) {
		return ServiceResponse.ok(bizz.getByMobileNumber(mobileNumber));
	}

	@PostMapping("/with-password")
	public ServiceResponse<Long> createWithPassword(@Validated(CreateGroup.class) @RequestBody UserDto dto) {
		return ServiceResponse.ok(bizz.createWithPassword(dto));
	}

	@PutMapping("/with-password")
	public ServiceResponse updateWithPassword(@Validated(UpdateGroup.class) @RequestBody UserDto dto) {
		bizz.updateWithPassowrd(dto);
		return ServiceResponse.ok();
	}
}
