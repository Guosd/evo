package com.ritoinfo.framework.evo.sp.sys.rest;

import com.ritoinfo.framework.evo.sp.base.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.sys.bizz.RoleBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.RoleCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.RoleDao;
import com.ritoinfo.framework.evo.sp.sys.dto.RoleDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-03-04 18:06
 */
@Slf4j
@RequestMapping("role")
@RestController
public class RoleRest extends BaseRest<RoleBizz, RoleDao, Role, Long, RoleCondition, RoleDto> {
}
