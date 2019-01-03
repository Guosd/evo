package com.ritoinfo.framework.evo.sp.auth.authorization.extend.password;

import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.UserDetailsBizz;
import com.ritoinfo.framework.evo.sp.auth.extend.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * User: Kyll
 * Date: 2018-12-20 11:31
 */
@Component
public class UsernamePasswordUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDetailsBizz userDetailsBizz;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserDetailsDto userDetailsDto = userDetailsBizz.getByUsername(UserDetailsCondition.builder().username(username).build());

		if (null == userDetailsDto) {
			throw new UsernameNotFoundException(username);
		}

		return LoginUser.builder()
				.id(userDetailsDto.getId())
				.username(userDetailsDto.getUsername())
				.password(userDetailsDto.getPassword())
				.email(userDetailsDto.getEmail())
				.mobileNumber(userDetailsDto.getMobileNumber())
				.authorities(Collections.emptySet())
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true).build();
	}
}
