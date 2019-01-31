package com.ritoinfo.framework.evo.sp.auth.authorization.bizz;

import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.assist.JwtAssist;
import com.ritoinfo.framework.evo.sp.auth.assist.RedisAssist;
import com.ritoinfo.framework.evo.sp.auth.common.AuthConst;
import com.ritoinfo.framework.evo.sp.auth.common.VerifyResult;
import com.ritoinfo.framework.evo.sp.auth.exception.LogoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2019-01-16 15:09
 */
@Slf4j
@Service
public class LogoutBizz {
	@Autowired
	private JwtAssist jwtAssist;
	@Autowired
	private RedisAssist redisAssist;
	@Autowired
	private RedisService redisService;

	public void logout(String accessToken) {
		VerifyResult verifyResult = jwtAssist.check(accessToken);
		if (VerifyResult.SUCCESS != verifyResult
				|| !redisService.delete(redisAssist.generate(AuthConst.BF_ACCESS_TOKEN, accessToken))
				|| !redisService.delete(redisAssist.generate(AuthConst.BF_REFRESH_TOKEN, accessToken))) {
			throw new LogoutException("注销异常");
		}
	}
}
