package com.ritoinfo.framework.evo.sp.auth.authorization.bizz;

import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.auth.authorization.model.LoginCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.model.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-12-24 11:12
 */
@ConditionalOnProperty(name = "evo.sp.auth.user-details-mode", havingValue = "default")
@Service
public class DefaultUserDetailsBizz implements UserDetailsBizz {
	@Autowired
	private LoginBizz loginBizz;

	@Override
	public UserDetailsDto getByUsername(UserDetailsCondition condition) {
		LoginDto loginDto = loginBizz.getOne(LoginCondition.builder().username(condition.getUsername()).build());
		return UserDetailsDto.builder().username(loginDto.getUsername()).password(loginDto.getPassword()).build();
	}

	@Override
	public UserDetailsDto getByMobileNumber(UserDetailsCondition condition) {
		LoginDto loginDto = loginBizz.getOne(LoginCondition.builder().mobileNumber(condition.getMobileNumber()).build());
		return UserDetailsDto.builder()
				.id(String.valueOf(loginDto.getId()))
				.username(loginDto.getUsername())
				.email(loginDto.getEmail())
				.mobileNumber(loginDto.getMobileNumber()).build();
	}
}
