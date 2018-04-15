package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.sp.auth.dto.TokenDto;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import com.ritoinfo.framework.evo.common.password.crypto.PasswordEncoder;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.dto.LoginDto;
import com.ritoinfo.framework.evo.sp.auth.dto.VerifyDto;
import com.ritoinfo.framework.evo.sp.auth.exception.PasswordInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.UserNotFoundException;
import com.ritoinfo.framework.evo.sp.sys.api.FuncApi;
import com.ritoinfo.framework.evo.sp.sys.api.UserApi;
import com.ritoinfo.framework.evo.sp.sys.dto.FuncDto;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-03-08 14:33
 */
@Slf4j
@Service
public class AuthBizz {
	@Autowired
	private UserApi userApi;
	@Autowired
	private FuncApi funcApi;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtToken jwtToken;
	@Autowired
	private RedisService redisService;

	public TokenDto authorize(LoginDto condition) {
		UserDto userDto = userApi.username(condition.getUsername()).getData();

		if (userDto == null) {
			throw new UserNotFoundException(condition.getUsername());
		}

		if (passwordEncoder.matches(condition.getPassword(), userDto.getPassword())) {
			String userId = String.valueOf(userDto.getId());
			String token = jwtToken.create(userId, userDto.getUsername(), userDto.getName(), userDto.getCode());

			redisService.set(token, userDto, jwtToken.parse(token).getJwtExpiration());

			return TokenDto.builder()
					.token(token)
					.refreshToken(jwtToken.createRefresh(userId, userDto.getUsername(), userDto.getName(), userDto.getCode()))
					.build();
		} else {
			throw new PasswordInvalidException(condition.getUsername());
		}
	}

	public void clear(String username) {
		redisService.delete(username);
	}

	public boolean verify(VerifyDto verifyDto) {
		for (FuncDto funcDto : funcApi.username(jwtToken.parse(verifyDto.getToken()).getUsername()).getData()) {
			if (verifyDto.getUri().equals(funcDto.getPrefix() + funcDto.getUri())) {
				return true;
			}
		}
		return false;
	}
}
