package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.common.jwt.model.UserContext;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.NetUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.assist.RedisKeyAssist;
import com.ritoinfo.framework.evo.sp.sys.api.UserApi;
import com.ritoinfo.framework.evo.sp.sys.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-04-21 17:08
 */
@Slf4j
@Service
public class AssistBizz {
	@Autowired
	private UserApi userApi;
	@Autowired
	private JwtToken jwtToken;
	@Autowired
	private RedisService redisService;

	public void updateLoginInfo(UserDto userDto, String token, HttpServletRequest request) {
		userDto.setLastLoginTime(userDto.getLoginTime());
		userDto.setLastLoginIp(userDto.getLoginIp());
		userDto.setLoginTime(DateUtil.now());
		userDto.setLoginIp(NetUtil.getRemoteAddr(request));

		if (userDto.getLastLoginTime() == null || StringUtil.isBlank(userDto.getLastLoginIp())) {
			userDto.setLastLoginTime(userDto.getLoginTime());
			userDto.setLastLoginIp(userDto.getLoginIp());
		}

		userApi.update(userDto, token);
	}

	public String createAndSaveToken(UserDto userDto) {
		return createAndSaveToken(userDto, userDto.getMobileNumber());
	}

	public String createAndSaveToken(UserDto userDto, String mobileNumber) {
		UserContext userContext = new UserContext();
		userContext.setId(String.valueOf(userDto.getId()));
		userContext.setUsername(userDto.getUsername());
		userContext.setName(userDto.getName());
		userContext.setCode(userDto.getCode());
		userContext.setMobileNumber(mobileNumber);
		return createAndSaveToken(userContext);
	}

	public String createAndSaveToken(UserContext userContext) {
		// 相同用户登录，删除前次登录token
		String onlineKey= RedisKeyAssist.generate("ONLINE", userContext.getMobileNumber());
		String onlineToken = redisService.getString(onlineKey);
		if (StringUtil.isNotBlank(onlineToken)) {
			log.info("用户[" + userContext.getMobileNumber() + "]的令牌[" + onlineToken + "]被重置");

			redisService.delete(RedisKeyAssist.generate("TOKEN", onlineToken));
			redisService.delete(RedisKeyAssist.generate("REFRESH_TOKEN", onlineToken));
			redisService.delete(RedisKeyAssist.generate("OLD_TOKEN", onlineToken));
		}

		// 清空 token 过期时间，因为从 Claims 中获取 Date 类型的数据，结果是 LONG 类型， 会引起 bean 赋值错误
		userContext.setJwtExpiration(null);

		// 生成 token
		Map<String, Object> map = BeanUtil.beanToMap(userContext);
		String token = jwtToken.create(userContext.getUsername(), map);
		String refreshToken = jwtToken.createRefresh(userContext.getUsername(), map);
		Date tokenExpiration = jwtToken.parse(token).getJwtExpiration();

		// 设置缓存
		redisService.set(RedisKeyAssist.generate("TOKEN", token), userContext, tokenExpiration);
		redisService.set(RedisKeyAssist.generate("REFRESH_TOKEN", token), refreshToken, jwtToken.parse(refreshToken).getJwtExpiration());
		redisService.set(onlineKey, token, tokenExpiration);

		return token;
	}
}
