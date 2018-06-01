package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.common.jwt.model.UserContext;
import com.ritoinfo.framework.evo.common.password.crypto.PasswordEncoder;
import com.ritoinfo.framework.evo.common.uitl.ArrayUtil;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.base.starter.exception.UserContextNotExistException;
import com.ritoinfo.framework.evo.sp.base.starter.session.SessionHolder;
import com.ritoinfo.framework.evo.sp.sys.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.UserDao;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import com.ritoinfo.framework.evo.sp.sys.entity.User;
import com.ritoinfo.framework.evo.sp.sys.exception.UserExistedException;
import com.ritoinfo.framework.evo.sp.sys.exception.UserRoleInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:31
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class UserBizz extends BaseBizz<UserDao, User, Long, UserCondition, UserDto> {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleBizz roleBizz;

	public UserDto getWithRole(Long id) {
		UserDto userDto = super.get(id);
		userDto.setRoleDtoList(roleBizz.findByUserId(id));
		return userDto;
	}

	public UserDto getByUsername(String username) {
		UserCondition condition = new UserCondition();
		condition.setUsername(username);

		List<User> list = dao.find(condition);
		return list.isEmpty() ? null : BaseHelper.toDto(list.get(0));
	}

	public UserDto getByMobileNumber(String mobileNumber) {
		UserCondition condition = new UserCondition();
		condition.setMobileNumber(mobileNumber);

		List<User> list = dao.find(condition);
		return list.isEmpty() ? null : BaseHelper.toDto(list.get(0));
	}

	// TODO 兼容 SCFW
	public UserDto getUserContext() {
		UserContext userContext = SessionHolder.getUserContext();
		if (userContext == null) {
			throw new UserContextNotExistException();
		}

		UserDto userDto = new UserDto();
		userDto.setId(userContext.getId(Long.class));
		userDto.setUsername(userContext.getUsername());
		userDto.setName(userContext.getName());
		userDto.setCode(userContext.getCode());
		userDto.setMobileNumber(userContext.getMobileNumber());
		return userDto;
	}

	@Transactional
	public Long createAll(UserDto dto) {
		UserDto existUserDto = getByMobileNumber(dto.getMobileNumber());
		if (existUserDto != null) {
			throw new UserExistedException(dto.getMobileNumber());
		}

		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		Long id = this.create(dto);
		dto.setId(id);

		Long[] roleIds = dto.getRoleIds();
		if (ArrayUtil.isNotEmpty(roleIds)) {
			if (ArrayUtil.isValid(roleIds)) {
				dao.insertWithRole(BaseHelper.dtoToMap(dto));
			} else {
				throw new UserRoleInvalidException(Arrays.toString(roleIds));
			}
		}

		return id;
	}

	@Transactional
	public void updateWithRole(UserDto dto) {
		UserDto oldUserDto = this.get(dto.getId());
		dto.setPassword(oldUserDto.getPassword());
		this.update(dto);

		Long[] roleIds = dto.getRoleIds();
		if (ArrayUtil.isEmpty(roleIds)) {
			dao.deleteWithRole(dto.getId());
		} else {
			if (ArrayUtil.isValid(roleIds)) {
				dao.deleteWithRole(dto.getId());
				dao.insertWithRole(BaseHelper.dtoToMap(dto));
			} else {
				throw new UserRoleInvalidException(Arrays.toString(roleIds));
			}
		}
	}

	@Transactional
	public void updatePassowrd(UserDto dto) {
		UserDto oldUserDto = this.get(dto.getId());
		oldUserDto.setPassword(passwordEncoder.encode(dto.getPassword()));
		this.update(oldUserDto);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		dao.deleteWithRole(id);

		super.delete(id);
	}

	@Transactional
	public void deleteByRole(Long roleId) {
		dao.deleteByRole(roleId);
	}
}
