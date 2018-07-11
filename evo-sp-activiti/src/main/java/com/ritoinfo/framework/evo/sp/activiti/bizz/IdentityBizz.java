package com.ritoinfo.framework.evo.sp.activiti.bizz;

import com.ritoinfo.framework.evo.sp.activiti.dto.IdentityDto;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.GroupProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.UserProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.service.IdentityServiceProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2018-06-08 15:35
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class IdentityBizz {
	@Autowired
	private IdentityServiceProxy identityServiceProxy;

	@Transactional
	public void create(IdentityDto identityDto) {
		String username = identityDto.getUsername();
		UserProxy userProxy = identityServiceProxy.getUserById(username);
		if (userProxy == null) {
			userProxy = identityServiceProxy.createUser(username);
		}

		String groupCode = identityDto.getGroupCode();
		GroupProxy groupProxy = identityServiceProxy.getGroupById(groupCode);
		if (groupProxy == null) {
			groupProxy = identityServiceProxy.createGroup(groupCode);
		}

		identityServiceProxy.deleteMembership(userProxy.getId(), groupProxy.getId());
		identityServiceProxy.createMembership(userProxy.getId(), groupProxy.getId());
	}

	public void deleteByUser(String username) {
		identityServiceProxy.deleteUser(username);
	}

	public void deleteByGroup(String groupCode) {
		identityServiceProxy.deleteGroup(groupCode);
	}
}
