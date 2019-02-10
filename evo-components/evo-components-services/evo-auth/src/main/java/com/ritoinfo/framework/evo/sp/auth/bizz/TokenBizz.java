package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.model.UserContext;
import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.auth.assist.JwtAssist;
import com.ritoinfo.framework.evo.sp.auth.assist.RedisAssist;
import com.ritoinfo.framework.evo.sp.auth.assist.VerifyResult;
import com.ritoinfo.framework.evo.sp.auth.config.JwtConfig;
import com.ritoinfo.framework.evo.sp.auth.exception.AccessTokenInvalidException;
import com.ritoinfo.framework.evo.sp.auth.exception.RefreshTokenNotFoundException;
import com.ritoinfo.framework.evo.sp.auth.exception.RefreshTokenVerifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2019-01-16 15:49
 */
@Slf4j
@Service
public class TokenBizz {
	@Autowired
	private JwtAssist jwtAssist;
	@Autowired
	private RedisAssist redisAssist;
	@Autowired
	private RedisService redisService;
	@Autowired
	private JwtConfig jwtConfig;

	public String createToken(UserDetailsDto userDetailsDto) {
		UserContext userContext = new UserContext();
		userContext.setId(String.valueOf(userDetailsDto.getId()));
		userContext.setUsername(userDetailsDto.getUsername());
		userContext.setName(userDetailsDto.getName());
		userContext.setCode(userDetailsDto.getCode());
		userContext.setMobileNumber(userDetailsDto.getMobileNumber());
		return createToken(userContext);
	}

	public String tryRefresh(String token) {
		return redisService.getString(redisAssist.generate(Const.BF_OLD_TOKEN, token));
	}

	public String refresh(String token) {
		String refreshToken = redisService.getString(redisAssist.generate(Const.BF_REFRESH_TOKEN, token));
		if (StringUtil.isBlank(refreshToken)) {
			throw new RefreshTokenNotFoundException(token);
		}

		VerifyResult verifyResult = jwtAssist.check(refreshToken);
		if (VerifyResult.SUCCESS == verifyResult) {
			String newToken = createToken(jwtAssist.parse(refreshToken));

			redisService.set(redisAssist.generate(Const.BF_OLD_TOKEN, token), newToken, 1000L * jwtConfig.getOldExpirationTime());
			redisService.delete(redisAssist.generate(Const.BF_REFRESH_TOKEN, token));

			return newToken;
		} else {
			throw new RefreshTokenVerifyException(refreshToken);
		}
	}

	public UserContext check(String accessToken) {
		VerifyResult verifyResult = jwtAssist.check(accessToken);
		log.debug("检查 access token 有效性，{}", verifyResult);

		UserContext userContext;
		if (VerifyResult.SUCCESS == verifyResult) {
			userContext = jwtAssist.parse(accessToken);
		} else if (VerifyResult.EXPIRED == verifyResult) {
			String newAccessToken = tryRefresh(accessToken);
			if (StringUtil.isBlank(newAccessToken)) {
				newAccessToken = refresh(accessToken);
			}

			if (StringUtil.isBlank(newAccessToken)) {
				throw new AccessTokenInvalidException(accessToken);
			}

			userContext = jwtAssist.parse(newAccessToken);
			userContext.setAccessToken(newAccessToken);
		} else {
			throw new AccessTokenInvalidException(accessToken);
		}

		return userContext;
	}

	private String createToken(UserContext userContext) {
		String onlineKey = redisAssist.generate(Const.BF_ONLINE, userContext.getId());
		String onlineToken = redisService.getString(onlineKey);
		if (StringUtil.isNotBlank(onlineToken)) {
			log.info("用户{}重新登录，令牌被重置", userContext.getMobileNumber(), onlineToken);

			redisService.delete(redisAssist.generate(Const.BF_ACCESS_TOKEN, onlineToken));
			redisService.delete(redisAssist.generate(Const.BF_REFRESH_TOKEN, onlineToken));
			redisService.delete(redisAssist.generate(Const.BF_OLD_TOKEN, onlineToken));
		}

		// 生成 access token 和 refresh token
		String accessToken = jwtAssist.createAccess(userContext);
		String refreshToken = jwtAssist.createRefresh(userContext);
		// 获取 token 过期时间
		Date accessTokenExpiration = jwtAssist.extractExpiration(accessToken);
		Date refreshTokenExpiration = jwtAssist.extractExpiration(refreshToken);
		// 设置缓存
		redisService.set(redisAssist.generate(Const.BF_ACCESS_TOKEN, accessToken), userContext, accessTokenExpiration);
		redisService.set(redisAssist.generate(Const.BF_REFRESH_TOKEN, accessToken), refreshToken, refreshTokenExpiration);
		redisService.set(onlineKey, accessToken, accessTokenExpiration);

		return accessToken;
	}
}
