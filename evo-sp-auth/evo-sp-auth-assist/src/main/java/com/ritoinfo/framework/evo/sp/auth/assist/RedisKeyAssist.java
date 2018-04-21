package com.ritoinfo.framework.evo.sp.auth.assist;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.data.redis.config.RedisKeyGenerator;

/**
 * User: Kyll
 * Date: 2018-04-21 23:48
 */
public class RedisKeyAssist {
	public static String generate(String bizzFlag, String key) {
		return RedisKeyGenerator.generate(Const.SPRING_APPLICATION_NAME_AUTH, "com.ritoinfo.framework.evo.sp.auth.bizz.AuthBizz", bizzFlag, key);
	}
}
