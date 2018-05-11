package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.RoleCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.RoleDao;
import com.ritoinfo.framework.evo.sp.sys.dto.RoleDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private FuncBizz funcBizz;

	public RoleDto getWithFunc(Long id) {
		RoleDto roleDto = super.get(id);
		roleDto.setFuncDtoList(funcBizz.getByRole(id));
		return roleDto;
	}

	public List<RoleDto> getByUserId(Long userId) {
		return BaseHelper.toDto(dao.getByUserId(userId));
	}

	public List<RoleDto> getByUsername(String username) {
		return BaseHelper.toDto(dao.getByUsername(username));
	}

	@Transactional
	@Override
	public Long create(RoleDto dto) {
		Long id = super.create(dto);
		dto.setId(id);

		dao.insertRoleFunc(BaseHelper.dtoToMap(dto));
		return id;
	}

	@Transactional
	@Override
	public void update(RoleDto dto) {
		super.update(dto);

		dao.deleteRoleFunc(dto.getId());
		dao.insertRoleFunc(BaseHelper.dtoToMap(dto));
	}
}
