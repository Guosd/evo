package com.ritoinfo.framework.evo.sp.auth.authorization.bizz;

import com.ritoinfo.framework.evo.sp.auth.authorization.api.UserDetailsApi;
import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2018-12-24 11:12
 */
@ConditionalOnProperty(name = "evo.sp.auth.user-details-mode", havingValue = "service")
@Service
public class ServiceUserDetailsBizz implements UserDetailsBizz {
	@Autowired
	private UserDetailsApi userDetailsApi;

	@Override
	public UserDetailsDto getByUsername(UserDetailsCondition condition) {
		return userDetailsApi.getByUsername(condition.getUsername()).getData();
	}

	@Override
	public UserDetailsDto getByMobileNumber(UserDetailsCondition condition) {
		return userDetailsApi.getByMobileNumber(condition.getMobileNumber()).getData();
	}
}
