package com.github.framework.evo.sys.rest;

import com.github.framework.evo.sys.bizz.PermissionBizz;
import com.github.framework.evo.sys.condition.PermissionCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2019-01-02 14:43
 */
@Slf4j
@RequestMapping("/permission")
@RestController
public class PermissionRest {
	@Autowired
	private PermissionBizz permissionBizz;

	@PostMapping("/check")
	public Boolean check(@RequestBody PermissionCondition condition) {
		return permissionBizz.check(condition);
	}
}
