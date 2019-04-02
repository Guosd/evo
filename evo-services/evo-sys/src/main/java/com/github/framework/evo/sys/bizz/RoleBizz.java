package com.github.framework.evo.sys.bizz;

import com.github.framework.evo.common.uitl.ArrayUtil;
import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.base.bizz.BaseXmlBizz;
import com.github.framework.evo.sys.condition.RoleCondition;
import com.github.framework.evo.sys.dao.RoleDao;
import com.github.framework.evo.sys.dto.RoleDto;
import com.github.framework.evo.sys.entity.Role;
import com.github.framework.evo.sys.exception.RoleFuncInvalidException;
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
public class RoleBizz extends BaseXmlBizz<RoleDao, Role, Long, RoleDto> {
	@Autowired
	private FuncBizz funcBizz;
	@Autowired
	private UserBizz userBizz;

	public RoleDto getWithFunc(Long id) {
		RoleDto roleDto = super.get(id);
		roleDto.setFuncDtoList(funcBizz.findByRoleWithMicro(id));
		return roleDto;
	}

	public RoleDto getByCode(String code) {
		RoleCondition condition = new RoleCondition();
		condition.setCode(code);

		List<Role> roleList =  dao.find(condition);
		return roleList.isEmpty() ? null : toDto(roleList.get(0));
	}

	public List<RoleDto> findByUserId(Long userId) {
		return toDto(dao.findByUserId(userId));
	}

	public List<RoleDto> findByUsername(String username) {
		return toDto(dao.findByUsername(username));
	}

	@Transactional
	@Override
	public Long create(RoleDto dto) {
		Long id = super.create(dto);
		dto.setId(id);

		Long[] funcIds = dto.getFuncIds();
		if (ArrayUtil.isNotEmpty(funcIds)) {
			if (ArrayUtil.isValid(funcIds)) {
				dao.insertWithFunc(BaseHelper.toMap(dto));
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
				dao.insertWithFunc(BaseHelper.toMap(dto));
			} else {
				throw new RoleFuncInvalidException(Arrays.toString(funcIds));
			}
		}
	}

	@Transactional
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
