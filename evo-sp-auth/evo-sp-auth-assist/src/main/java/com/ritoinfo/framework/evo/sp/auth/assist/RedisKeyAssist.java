package com.ritoinfo.framework.evo.sp.auth.assist;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.data.redis.config.RedisKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-04-21 23:48
 */
@Component
public class RedisKeyAssist {
	@Autowired
	private RedisKeyGenerator redisKeyGenerator;

	public String generate(String bizzFlag, String key) {
		return redisKeyGenerator.generate(Const.SPRING_APPLICATION_NAME_AUTH, "com.ritoinfo.framework.evo.sp.auth.bizz.AuthBizz", bizzFlag, key);
	}
}
