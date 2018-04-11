package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.RoleCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.RoleDao;
import com.ritoinfo.framework.evo.sp.sys.dto.RoleDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-04 18:05
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class RoleBizz extends BaseBizz<RoleDao, Role, Long, RoleCondition, RoleDto> {
	public List<RoleDto> getByUserId(Long userId) {
		return BaseHelper.toDto(dao.getByUserId(userId));
	}
}
