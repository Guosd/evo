package com.ritoinfo.framework.evo.sp.sys.rest;

import com.ritoinfo.framework.evo.sp.base.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.sys.bizz.RoleBizz;
import com.ritoinfo.framework.evo.sp.sys.bizz.UserBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.UserDao;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import com.ritoinfo.framework.evo.sp.sys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@Autowired
	private RoleBizz roleBizz;

	@GetMapping("/username/{username}")
	public ServiceResponse username(@PathVariable String username) {
		return ServiceResponse.ok(BaseHelper.toDto(bizz.getByUsername(username)));
	}
}
