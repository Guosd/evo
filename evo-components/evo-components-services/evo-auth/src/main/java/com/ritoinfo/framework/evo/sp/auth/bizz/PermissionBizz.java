package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.sp.auth.api.model.RbacDto;
import com.ritoinfo.framework.evo.sp.auth.assist.JwtAssist;
import com.ritoinfo.framework.evo.sp.auth.assist.RedisAssist;
import com.ritoinfo.framework.evo.sp.auth.model.PermissionParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2019-01-16 15:50
 */
@Slf4j
@Service
public class PermissionBizz {
	@Autowired
	private RbacBizz rbacBizz;
	@Autowired
	private RedisAssist redisAssist;
	@Autowired
	private JwtAssist jwtAssist;

	public boolean check(PermissionParam permissionParam) {
		String accessToken = permissionParam.getToken();
		return redisAssist.existAccessToken(accessToken)
				? rbacBizz.check(RbacDto.builder().userId(jwtAssist.parse(accessToken).getId()).uri(permissionParam.getUri()).method(permissionParam.getMethod()).build())
				: Boolean.FALSE;
	}
}
