package com.github.framework.evo.sys.rest;

import com.github.framework.evo.base.rest.BaseRest;
import com.github.framework.evo.common.validate.group.CreateGroup;
import com.github.framework.evo.sys.bizz.RoleBizz;
import com.github.framework.evo.sys.condition.RoleCondition;
import com.github.framework.evo.sys.dto.RoleDto;
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
public class RoleRest extends BaseRest<RoleBizz, Long, RoleDto, RoleCondition> {
	@GetMapping("/id/{id}/func")
	public RoleDto getWithFunc(@PathVariable Long id) {
		return bizz.getWithFunc(id);
	}

	@GetMapping("/code/{code}")
	public RoleDto getByCode(@PathVariable String code) {
		return bizz.getByCode(code);
	}

	@GetMapping("/user/id/{userId}")
	public List<RoleDto> findByUserId(@PathVariable Long userId) {
		return bizz.findByUserId(userId);
	}

	@GetMapping("/user/username/{username}")
	public List<RoleDto> findByUsername(@PathVariable String username) {
		return bizz.findByUsername(username);
	}

	@PostMapping
	public Long create(@Validated(CreateGroup.class) @RequestBody RoleDto roleDto) {
		return bizz.create(roleDto);
	}
}
