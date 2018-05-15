package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.password.crypto.PasswordEncoder;
import com.ritoinfo.framework.evo.sp.auth.dto.PcLoginDto;
import com.ritoinfo.framework.evo.sp.auth.exception.PasswordInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.UserNotFoundException;
import com.ritoinfo.framework.evo.sp.sys.api.UserApi;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2018-04-21 16:43
 */
@Slf4j
@Service
public class AuthBizz {
	@Autowired
	private UserApi userApi;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AssistBizz assistBizz;

	public String authorize(PcLoginDto loginDto, HttpServletRequest request) {
		UserDto userDto = userApi.getByUsername(loginDto.getUsername()).getData();
		if (userDto == null) {
			throw new UserNotFoundException(loginDto.getUsername());
		}

		if (passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword())) {
			String token = assistBizz.createAndSaveToken(userDto);
			assistBizz.updateLoginInfo(userDto, token, request);
			return token;
		} else {
			throw new PasswordInvalidException(loginDto.getUsername());
		}
	}
}
