package com.ritoinfo.framework.evo.sp.sys.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.sp.base.exception.BizzException;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.CreateGroup;
import com.ritoinfo.framework.evo.sp.sys.bizz.RoleBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.RoleCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.RoleDao;
import com.ritoinfo.framework.evo.sp.sys.dto.RoleDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-04 18:06
 */
@Slf4j
@RequestMapping("role")
@RestController
public class RoleRest extends BaseRest<RoleBizz, RoleDao, Role, Long, RoleCondition, RoleDto> {
	@GetMapping("/id/{id}/func")
	public ServiceResponse<RoleDto> getWithFunc(@PathVariable Long id) {
		return ServiceResponse.ok(bizz.getWithFunc(id));
	}

	@GetMapping("/code/{code}")
	public ServiceResponse<RoleDto> getByCode(@PathVariable String code) {
		return ServiceResponse.ok(bizz.getByCode(code));
	}

	@GetMapping("/user/id/{userId}")
	public ServiceResponse<List<RoleDto>> findByUserId(@PathVariable Long userId) {
		return ServiceResponse.ok(bizz.findByUserId(userId));
	}

	@GetMapping("/user/username/{username}")
	public ServiceResponse<List<RoleDto>> findByUsername(@PathVariable String username) {
		return ServiceResponse.ok(bizz.findByUsername(username));
	}

	@PostMapping
	public ServiceResponse<Long> create(@Validated(CreateGroup.class) @RequestBody RoleDto roleDto) {
		try {
			return ServiceResponse.ok(bizz.create(roleDto));
		} catch (BizzException e) {
			throw new RestException(Const.RC_SYS_ROLE_FUNC);
		}
	}
}
