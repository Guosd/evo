package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.jwt.model.TokenInfo;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import com.ritoinfo.framework.evo.common.password.crypto.PasswordEncoder;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.condition.AuthCondition;
import com.ritoinfo.framework.evo.sp.auth.exception.PasswordInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.UserNotFoundException;
import com.ritoinfo.framework.evo.sp.auth.infa.SysUserInfa;
import com.ritoinfo.framework.evo.sp.auth.infa.dto.UserDto;
import com.ritoinfo.framework.evo.sp.base.infa.model.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-03-08 14:33
 */
@Service
public class AuthBizz {
	@Autowired
	private SysUserInfa sysUserInfa;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtToken jwtToken;
	@Autowired
	private RedisService redisService;

	public TokenInfo authorize(AuthCondition condition) {
		ServiceResponse<UserDto> serviceResponse = sysUserInfa.getByUsername(condition.getUsername());
		UserDto userDto = serviceResponse.getData();

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

			redisService.add(userId, token, jwtToken.parse(token).getJwtExpiration());

			return tokenInfo;
		} else {
			throw new PasswordInvalidException(condition.getUsername());
		}
	}

	public void clear(String username) {
		redisService.delete(username);
	}
}
