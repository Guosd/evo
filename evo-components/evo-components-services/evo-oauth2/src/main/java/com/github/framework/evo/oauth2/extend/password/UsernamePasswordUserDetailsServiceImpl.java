package com.github.framework.evo.oauth2.extend.password;

import com.github.framework.evo.auth.api.IamApi;
import com.github.framework.evo.auth.model.UserDetailsDto;
import com.github.framework.evo.oauth2.extend.LoginUser;
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
	private IamApi iamApi;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserDetailsDto userDetailsDto = iamApi.getByUsername(username).getData();

		if (null == userDetailsDto) {
			throw new UsernameNotFoundException(username);
		}

		return LoginUser.builder()
				.id(userDetailsDto.getId())
				.name(userDetailsDto.getName())
				.code(userDetailsDto.getCode())
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
