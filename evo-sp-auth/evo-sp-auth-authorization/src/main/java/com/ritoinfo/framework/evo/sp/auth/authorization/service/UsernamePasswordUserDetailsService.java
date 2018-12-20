package com.ritoinfo.framework.evo.sp.auth.authorization.service;

import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.LoginBizz;
import com.ritoinfo.framework.evo.sp.auth.authorization.model.LoginCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.model.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * User: Kyll
 * Date: 2018-12-20 11:31
 */
@Service
public class UsernamePasswordUserDetailsService implements UserDetailsService {
	@Autowired
	private LoginBizz loginBizz;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginDto loginDto = loginBizz.getOne(LoginCondition.builder().username(username).build());

		if (null == loginDto) {
			throw new UsernameNotFoundException(username);
		}

		return new User(loginDto.getUsername(), loginDto.getPassword(), new ArrayList<>());
	}
}
