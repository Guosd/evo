package com.github.framework.evo.activiti.rest;

import com.github.framework.evo.activiti.bizz.IdentityBizz;
import com.github.framework.evo.activiti.dto.IdentityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-06-08 15:36
 */
@Slf4j
@RequestMapping("/identity")
@RestController
public class IdentityRest {
	@Autowired
	private IdentityBizz identityBizz;

	@PostMapping
	public void create(@RequestBody IdentityDto identityDto) {
		identityBizz.create(identityDto);
	}

	@PostMapping("/createNew")
	public void createNew(String username, String code) {
		IdentityDto dto = new IdentityDto();
		dto.setUsername(username);
		dto.setGroupCode(code);
		identityBizz.create(dto);
	}

	@DeleteMapping("/user/{username}")
	public void deleteByUser(@PathVariable String username) {
		identityBizz.deleteByUser(username);
	}

	@DeleteMapping("/group/{groupCode}")
	public void deleteByGroup(@PathVariable String groupCode) {
		identityBizz.deleteByGroup(groupCode);
	}
}
