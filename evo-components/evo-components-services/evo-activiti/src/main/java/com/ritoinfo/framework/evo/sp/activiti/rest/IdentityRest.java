package com.ritoinfo.framework.evo.sp.activiti.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.exception.BizzException;
import com.ritoinfo.framework.evo.common.exception.RestException;
import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.activiti.bizz.IdentityBizz;
import com.ritoinfo.framework.evo.sp.activiti.dto.IdentityDto;
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
@RequestMapping("identity")
@RestController
public class IdentityRest {
	@Autowired
	private IdentityBizz identityBizz;

	@PostMapping
	public ServiceResponse create(@RequestBody IdentityDto identityDto) {
		try {
			identityBizz.create(identityDto);
			return ServiceResponse.ok();
		} catch (BizzException e) {
			throw new RestException(Const.RC_ACTIVITI_ID_CREATE, e);
		}
	}

	@PostMapping("/createNew")
	public ServiceResponse createNew(String username, String code) {
		try {
			IdentityDto dto = new IdentityDto();
			dto.setUsername(username);
			dto.setGroupCode(code);
			identityBizz.create(dto);
			return ServiceResponse.ok();
		} catch (BizzException e) {
			throw new RestException(Const.RC_ACTIVITI_ID_CREATE, e);
		}
	}

	@DeleteMapping("/user/{username}")
	public ServiceResponse deleteByUser(@PathVariable String username) {
		try {
			identityBizz.deleteByUser(username);
			return ServiceResponse.ok();
		} catch (BizzException e) {
			throw new RestException(Const.RC_ACTIVITI_ID_USER_DELETE, e);
		}
	}

	@DeleteMapping("/group/{groupCode}")
	public ServiceResponse deleteByGroup(@PathVariable String groupCode) {
		try {
			identityBizz.deleteByGroup(groupCode);
			return ServiceResponse.ok();
		} catch (BizzException e) {
			throw new RestException(Const.RC_ACTIVITI_ID_GROUP_DELETE, e);
		}
	}
}
