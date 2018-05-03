package com.ritoinfo.framework.evo.sp.sys.rest;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.sys.bizz.RoleBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.RoleCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.RoleDao;
import com.ritoinfo.framework.evo.sp.sys.dto.RoleDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/user/id/{userId}")
	public ServiceResponse<List<RoleDto>> userId(@PathVariable Long userId) {
		return ServiceResponse.ok(bizz.getByUserId(userId));
	}

	@GetMapping("/user/username/{username}")
	public ServiceResponse<List<RoleDto>> username(@PathVariable String username) {
		return ServiceResponse.ok(bizz.getByUsername(username));
	}
}
