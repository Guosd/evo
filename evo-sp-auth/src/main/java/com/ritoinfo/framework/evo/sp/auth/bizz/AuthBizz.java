package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.jwt.model.UserContext;
import com.ritoinfo.framework.evo.common.jwt.model.VerifyResult;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import com.ritoinfo.framework.evo.common.password.crypto.PasswordEncoder;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.NetUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.dto.LoginDto;
import com.ritoinfo.framework.evo.sp.auth.dto.VerifyDto;
import com.ritoinfo.framework.evo.sp.auth.exception.PasswordInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.RefreshTokenNotFoundException;
import com.ritoinfo.framework.evo.sp.auth.exception.RefreshTokenVerifyException;
import com.ritoinfo.framework.evo.sp.auth.exception.UserNotFoundException;
import com.ritoinfo.framework.evo.sp.sys.api.FuncApi;
import com.ritoinfo.framework.evo.sp.sys.api.UserApi;
import com.ritoinfo.framework.evo.sp.sys.dto.FuncDto;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

	public String authorize(LoginDto loginDto, HttpServletRequest request) {
		UserDto userDto = userApi.username(loginDto.getUsername()).getData();
		if (userDto == null) {
			throw new UserNotFoundException(loginDto.getUsername());
		}

		if (passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword())) {
			userDto.setLastLoginTime(userDto.getLoginTime());
			userDto.setLastLoginIp(userDto.getLoginIp());
			userDto.setLoginTime(DateUtil.now());
			userDto.setLoginIp(NetUtil.getRemoteAddr(request));

			if (userDto.getLastLoginTime() == null || StringUtil.isBlank(userDto.getLastLoginIp())) {
				userDto.setLastLoginTime(userDto.getLoginTime());
				userDto.setLastLoginIp(userDto.getLoginIp());
			}

			String token = createAndSaveToken(UserContext.builder()
					.id(String.valueOf(userDto.getId()))
					.username(userDto.getUsername())
					.name(userDto.getName())
					.code(userDto.getCode()).build());

			userApi.update(userDto, token);

			return token;
		} else {
			throw new PasswordInvalidException(loginDto.getUsername());
		}
	}

	public boolean clear(String token) {
		if (VerifyResult.SUCCESS == jwtToken.verify(token)) {
			return redisService.delete(AuthBizz.class, "TOKEN", token) && redisService.delete(AuthBizz.class, "REFRESH_TOKEN", token);
		}
		return false;
	}

	public String tryRefresh(String token) {
		return redisService.getString(AuthBizz.class, "OLD_TOKEN", token);
	}

	public String refresh(String token) {
		String refreshToken = redisService.getString(AuthBizz.class, "REFRESH_TOKEN", token);
		if (StringUtil.isBlank(refreshToken)) {
			throw new RefreshTokenNotFoundException(token);
		}

		VerifyResult verifyResult = jwtToken.verify(refreshToken);
		if (VerifyResult.SUCCESS == verifyResult) {
			String newToken = createAndSaveToken(jwtToken.parse(refreshToken));
			redisService.set(AuthBizz.class, "OLD_TOKEN", token, newToken, 30000L);
			redisService.delete(AuthBizz.class, "REFRESH_TOKEN", token);
			return newToken;
		} else {
			throw new RefreshTokenVerifyException(refreshToken);
		}
	}

	public boolean verify(VerifyDto verifyDto) {
		if (redisService.exist(AuthBizz.class, "TOKEN", verifyDto.getToken())) {
			for (FuncDto funcDto : funcApi.username(jwtToken.parse(verifyDto.getToken()).getUsername()).getData()) {
				if (verifyDto.getUri().equals(funcDto.getPrefix() + funcDto.getUri())) {
					return true;
				}
			}
		}
		return false;
	}

	private String createAndSaveToken(UserContext userContext) {
		String token = jwtToken.create(userContext.getId(), userContext.getUsername(), userContext.getName(), userContext.getCode());
		String refreshToken = jwtToken.createRefresh(userContext.getId(), userContext.getUsername(), userContext.getName(), userContext.getCode());
		redisService.set(AuthBizz.class, "TOKEN", token, userContext, jwtToken.parse(token).getJwtExpiration());
		redisService.set(AuthBizz.class, "REFRESH_TOKEN", token, refreshToken, jwtToken.parse(refreshToken).getJwtExpiration());
		return token;
	}
}
