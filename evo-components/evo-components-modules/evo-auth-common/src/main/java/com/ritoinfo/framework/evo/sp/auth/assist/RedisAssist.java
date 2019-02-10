package com.ritoinfo.framework.evo.sp.auth.assist;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.data.redis.RedisKeyGenerator;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.sp.auth.config.VerifyCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-04-21 23:48
 */
@Component
public class RedisAssist {
	private static final String SPRING_APPLICATION_NAME = "evo-sp-auth";// 虚拟应用名称
	private static final String CLASS_NAME = "com.ritoinfo.framework.evo.sp.auth.bizz.AuthBizz";// 虚拟类名

	@Autowired
	private RedisKeyGenerator redisKeyGenerator;
	@Autowired
	private RedisService redisService;
	@Autowired
	private VerifyCodeConfig verifyCodeConfig;

	public String generate(String bizzFlag, String key) {
		return redisKeyGenerator.generate(SPRING_APPLICATION_NAME, CLASS_NAME, bizzFlag, key);
	}

	public void saveVerifyCode(String bizzFlag, String mobileNumber, String verifyCode) {
		redisService.set(generate(Const.VERIFY_CODE_PREFIX + bizzFlag, mobileNumber), verifyCode, 1000L * 60 * verifyCodeConfig.getExpirationTime());
	}

	public String getVerifyCode(String bizzFlag, String mobileNumber) {
		return redisService.getString(generate(Const.VERIFY_CODE_PREFIX + bizzFlag, mobileNumber));
	}

	public void deleteVerifyCode(String mobileNumber) {
		redisService.delete(generate(Const.VERIFY_CODE_PREFIX + Const.VERIFY_CODE_SIGN_IN, mobileNumber));
		redisService.delete(generate(Const.VERIFY_CODE_PREFIX + Const.VERIFY_CODE_SIGN_UP, mobileNumber));
	}

	public boolean existAccessToken(String accessToken) {
		return redisService.exist(generate(Const.BF_ACCESS_TOKEN, accessToken));
	}
}
