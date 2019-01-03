package com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc;

import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.UserDetailsBizz;
import com.ritoinfo.framework.evo.sp.auth.extend.LoginUser;
import com.ritoinfo.framework.evo.sp.auth.extend.LoginUserDetails;
import com.ritoinfo.framework.evo.sp.auth.extend.LoginUserDetailsService;
import com.ritoinfo.framework.evo.sp.auth.extend.exception.MobileNumberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * User: Kyll
 * Date: 2018-12-20 11:31
 */
@Component
public class MobileNumberVerifyCodeUserDetailsServiceImpl implements LoginUserDetailsService {
	@Autowired
	private UserDetailsBizz userDetailsBizz;

	@Override
	public LoginUserDetails loadUserByMobileNumber(String mobileNumber) {
		UserDetailsDto userDetailsDto = userDetailsBizz.getByMobileNumber(UserDetailsCondition.builder().mobileNumber(mobileNumber).build());

		if (null == userDetailsDto) {
			throw new MobileNumberNotFoundException(mobileNumber);
		}

		return LoginUser.builder()
				.id(userDetailsDto.getId())
				.username(userDetailsDto.getUsername())
				.email(userDetailsDto.getEmail())
				.mobileNumber(userDetailsDto.getMobileNumber())
				.authorities(Collections.emptySet())
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true).build();
	}
}
