package com.ritoinfo.framework.evo.sp.auth.authorization.bizz;

import com.ritoinfo.framework.evo.sp.auth.api.UserDetailsApi;
import com.ritoinfo.framework.evo.sp.auth.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.auth.exception.PasswordInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.UserNotFoundException;
import com.ritoinfo.framework.evo.sp.auth.model.UsernamePasswordParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2019-01-16 11:30
 */
@Slf4j
@Service
public class UsernamePasswordBizz {
	@Autowired
	private UserDetailsApi userDetailsApi;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenBizz tokenBizz;

	public String login(UsernamePasswordParam loginDto, String remoteAddr) {
		UserDetailsDto userDetailsDto = userDetailsApi.getByUsername(loginDto.getUsername()).getData();
		if (userDetailsDto == null) {
			throw new UserNotFoundException(loginDto.getUsername());
		}

		if (passwordEncoder.matches(loginDto.getPassword(), userDetailsDto.getPassword())) {
			userDetailsApi.updateLoginInfo(userDetailsDto.getId(), remoteAddr);
			return tokenBizz.createToken(userDetailsDto);
		}

		throw new PasswordInvalidException(loginDto.getUsername());
	}
}
