package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.common.uitl.ArrayUtil;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.RoleCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.RoleDao;
import com.ritoinfo.framework.evo.sp.sys.dto.RoleDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Role;
import com.ritoinfo.framework.evo.sp.sys.exception.RoleFuncInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
	@Autowired
	private UserBizz userBizz;

	public RoleDto getWithFunc(Long id) {
		RoleDto roleDto = super.get(id);
		roleDto.setFuncDtoList(funcBizz.findByRoleWithMicro(id));
		return roleDto;
	}

	public List<RoleDto> findByUserId(Long userId) {
		return BaseHelper.toDto(dao.findByUserId(userId));
	}

	public List<RoleDto> findByUsername(String username) {
		return BaseHelper.toDto(dao.findByUsername(username));
	}

	@Transactional
	@Override
	public Long create(RoleDto dto) {
		Long id = super.create(dto);
		dto.setId(id);

		Long[] funcIds = dto.getFuncIds();
		if (ArrayUtil.isNotEmpty(funcIds)) {
			if (ArrayUtil.isValid(funcIds)) {
				dao.insertWithFunc(BaseHelper.dtoToMap(dto));
			} else {
				throw new RoleFuncInvalidException(Arrays.toString(funcIds));
			}
		}

		return id;
	}

	@Transactional
	@Override
	public void update(RoleDto dto) {
		super.update(dto);

		Long[] funcIds = dto.getFuncIds();
		if (ArrayUtil.isEmpty(funcIds)) {
			dao.deleteWithFunc(dto.getId());
		} else {
			if (ArrayUtil.isValid(funcIds)) {
				dao.deleteWithFunc(dto.getId());
				dao.insertWithFunc(BaseHelper.dtoToMap(dto));
			} else {
				throw new RoleFuncInvalidException(Arrays.toString(funcIds));
			}
		}
	}

	@Override
	public void delete(Long id) {
		dao.deleteWithFunc(id);

		userBizz.deleteByRole(id);

		super.delete(id);
	}

	@Transactional
	public void deleteByFunc(Long funcId) {
		dao.deleteByFunc(funcId);
	}
}
