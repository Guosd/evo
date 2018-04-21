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
		UserContext userContext = new UserContext();
		userContext.setId(String.valueOf(userDto.getId()));
		userContext.setUsername(userDto.getUsername());
		userContext.setName(userDto.getName());
		userContext.setCode(userDto.getCode());
		userContext.setMobileNumber(userDto.getMobileNumber());
		return createAndSaveToken(userContext);
	}

	public String createAndSaveToken(UserContext userContext) {
		userContext.setJwtExpiration(null);// 清空 token 过期时间，因为从 Claims 中获取 Date 类型的数据，结果是 LONG 类型， 会引起 bean 赋值错误
		Map<String, Object> map = BeanUtil.beanToMap(userContext);

		String token = jwtToken.create(userContext.getUsername(), map);
		String refreshToken = jwtToken.createRefresh(userContext.getUsername(), map);
		redisService.set(RedisKeyAssist.generate("TOKEN", token), userContext, jwtToken.parse(token).getJwtExpiration());
		redisService.set(RedisKeyAssist.generate("REFRESH_TOKEN", token), refreshToken, jwtToken.parse(refreshToken).getJwtExpiration());
		return token;
	}
}
