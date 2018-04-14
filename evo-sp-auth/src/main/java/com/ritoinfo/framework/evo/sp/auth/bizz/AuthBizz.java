package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.jwt.model.TokenInfo;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import com.ritoinfo.framework.evo.common.password.crypto.PasswordEncoder;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.condition.AuthCondition;
import com.ritoinfo.framework.evo.sp.auth.exception.PasswordInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.UserNotFoundException;
import com.ritoinfo.framework.evo.sp.auth.infa.ISysFuncService;
import com.ritoinfo.framework.evo.sp.auth.infa.ISysUserService;
import com.ritoinfo.framework.evo.sp.auth.infa.dto.FuncDto;
import com.ritoinfo.framework.evo.sp.auth.infa.dto.UserDto;
import com.ritoinfo.framework.evo.sp.base.infa.model.ServiceResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-08 14:33
 */
@Slf4j
@Service
public class AuthBizz {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysFuncService sysFuncService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtToken jwtToken;
	@Autowired
	private RedisService redisService;

	public TokenInfo authorize(AuthCondition condition) {
		ServiceResponseWrapper<UserDto> serviceResponseWrapper = sysUserService.getByUsername(condition.getUsername());
		UserDto userDto = serviceResponseWrapper.getData();

		if (userDto == null) {
			throw new UserNotFoundException(condition.getUsername());
		}

		if (passwordEncoder.matches(condition.getPassword(), userDto.getPassword())) {
			String userId = String.valueOf(userDto.getId());
			String token = jwtToken.create(userId, userDto.getUsername(), userDto.getName(), userDto.getCode());

			TokenInfo tokenInfo = TokenInfo.builder()
					.token(token)
					.refreshToken(jwtToken.createRefresh(userId, userDto.getUsername(), userDto.getName(), userDto.getCode()))
					.build();

			redisService.set(userId, token, jwtToken.parse(token).getJwtExpiration());
			redisService.set(token, userDto, jwtToken.parse(token).getJwtExpiration());

			return tokenInfo;
		} else {
			throw new PasswordInvalidException(condition.getUsername());
		}
	}

	public void clear(String username) {
		redisService.delete(username);
	}

	public boolean verify(String uri, String token) {
		for (FuncDto funcDto : sysFuncService.getByUsername(jwtToken.parse(token).getUsername()).getData()) {
			if (uri.equals(funcDto.getPrefix() + funcDto.getUri())) {
				return true;
			}
		}
		return false;
	}
}
