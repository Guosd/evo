package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.jwt.model.VerifyResult;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import com.ritoinfo.framework.evo.common.password.crypto.PasswordEncoder;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.assist.RedisKeyAssist;
import com.ritoinfo.framework.evo.sp.auth.dto.DefaultUserDto;
import com.ritoinfo.framework.evo.sp.auth.dto.VerifyDto;
import com.ritoinfo.framework.evo.sp.auth.exception.PasswordInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.RefreshTokenNotFoundException;
import com.ritoinfo.framework.evo.sp.auth.exception.RefreshTokenVerifyException;
import com.ritoinfo.framework.evo.sp.auth.exception.UserNotFoundException;
import com.ritoinfo.framework.evo.sp.sys.api.FuncApi;
import com.ritoinfo.framework.evo.sp.sys.api.UserApi;
import com.ritoinfo.framework.evo.sp.sys.dto.PermissionDto;
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
	private JwtToken jwtToken;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RedisService redisService;
	@Autowired
	private AssistBizz assistBizz;

	public String getDefaultUserToken(DefaultUserDto defaultUserDto, HttpServletRequest request) {
		UserDto userDto = userApi.getByUsername(defaultUserDto.getUsername()).getData();
		if (userDto == null) {
			throw new UserNotFoundException(defaultUserDto.getUsername());
		}

		if (passwordEncoder.matches(defaultUserDto.getPassword(), userDto.getPassword())) {
			String token = assistBizz.createAndSaveToken(userDto, defaultUserDto.getMobileNumber());
			assistBizz.updateLoginInfo(userDto, token, request);
			return token;
		} else {
			throw new PasswordInvalidException(defaultUserDto.getUsername());
		}
	}

	public boolean clear(String token) {
		if (VerifyResult.SUCCESS == jwtToken.verify(token)) {
			return redisService.delete(RedisKeyAssist.generate("TOKEN", token)) && redisService.delete(RedisKeyAssist.generate("REFRESH_TOKEN", token));
		}
		return false;
	}

	public String tryRefresh(String token) {
		return redisService.getString(RedisKeyAssist.generate("OLD_TOKEN", token));
	}

	public String refresh(String token) {
		String refreshToken = redisService.getString(RedisKeyAssist.generate("REFRESH_TOKEN", token));
		if (StringUtil.isBlank(refreshToken)) {
			throw new RefreshTokenNotFoundException(token);
		}

		VerifyResult verifyResult = jwtToken.verify(refreshToken);
		if (VerifyResult.SUCCESS == verifyResult) {
			String newToken = assistBizz.createAndSaveToken(jwtToken.parse(refreshToken));
			redisService.set(RedisKeyAssist.generate("OLD_TOKEN", token), newToken, 30000L);
			redisService.delete(RedisKeyAssist.generate("REFRESH_TOKEN", token));
			return newToken;
		} else {
			throw new RefreshTokenVerifyException(refreshToken);
		}
	}

	public boolean verify(VerifyDto verifyDto) {
		if (redisService.exist(RedisKeyAssist.generate("TOKEN", verifyDto.getToken()))) {
			for (PermissionDto permissionDto : funcApi.getByUsername(jwtToken.parse(verifyDto.getToken()).getUsername()).getData()) {
				String uri = permissionDto.getUri();
				uri = uri.contains("?") ? uri.substring(0, uri.indexOf('?')) : uri;

				if (verifyDto.getUri().equals(permissionDto.getPrefix() + uri) && verifyDto.getMethod().equalsIgnoreCase(permissionDto.getMethod())) {
					return true;
				}
			}
		}
		return false;
	}
}
