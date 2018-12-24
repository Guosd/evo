package com.ritoinfo.framework.evo.sp.auth.authorization.extend.service;

import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.UserDetailsBizz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * User: Kyll
 * Date: 2018-12-20 11:31
 */
@Component
public class UsernamePasswordUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDetailsBizz userDetailsBizz;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsDto userDetailsDto = userDetailsBizz.getByUsername(UserDetailsCondition.builder().username(username).build());

		if (null == userDetailsDto) {
			throw new UsernameNotFoundException(username);
		}

		return new User(userDetailsDto.getUsername(), userDetailsDto.getPassword(), new ArrayList<>());
	}
}
