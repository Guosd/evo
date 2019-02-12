package com.github.framework.evo.activiti.proxy.service;

import com.github.framework.evo.common.uitl.BeanUtil;
import com.github.framework.evo.activiti.proxy.entity.GroupProxy;
import com.github.framework.evo.activiti.proxy.entity.UserProxy;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-06-08 15:33
 */
@Slf4j
@Service
public class IdentityServiceProxy {
	@Autowired
	private IdentityService identityService;

	public void test() {

	}

	public UserProxy getUserById(String id) {
		log.info("查询用户: " + id);

		User user = identityService.createUserQuery().userId(id).singleResult();
		return user == null ? null : toUserProxy(user);
	}

	public GroupProxy getGroupById(String id) {
		log.info("查询用户组: " + id);

		Group group = identityService.createGroupQuery().groupId(id).singleResult();
		return group == null ? null : toGroupProxy(group);
	}

	public UserProxy createUser(String id) {
		log.info("建立用户: " + id);

		User user = identityService.newUser(id);
		identityService.saveUser(user);
		return toUserProxy(user);
	}

	public GroupProxy createGroup(String id) {
		log.info("建立用户组: " + id);

		Group group = identityService.newGroup(id);
		group.setType("assignment");
		identityService.saveGroup(group);
		return toGroupProxy(group);
	}

	public void createMembership(String userId, String groupId) {
		log.info("建立用户组关系: " + userId + ", " + groupId);

		identityService.createMembership(userId, groupId);
	}

	public void deleteUser(String id) {
		log.info("删除用户: " + id);

		identityService.deleteUser(id);
	}

	public void deleteGroup(String id) {
		log.info("删除用户组: " + id);

		identityService.deleteGroup(id);
	}

	public void deleteMembership(String userId, String groupId) {
		log.info("删除用户组关系: " + userId + ", " + groupId);

		identityService.deleteMembership(userId, groupId);
	}

	private UserProxy toUserProxy(User user) {
		UserProxy userProxy = new UserProxy();
		BeanUtil.copy(userProxy, user);
		return userProxy;
	}

	private GroupProxy toGroupProxy(Group group) {
		GroupProxy groupProxy = new GroupProxy();
		BeanUtil.copy(groupProxy, group);
		return groupProxy;
	}
}
