package com.ritoinfo.framework.evo.sp.auth.authorization.bizz;

import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsDto;

/**
 * User: Kyll
 * Date: 2018-12-24 11:58
 */
public interface UserDetailsBizz {
	UserDetailsDto getByUsername(UserDetailsCondition condition);

	UserDetailsDto getByMobileNumber(UserDetailsCondition condition);
}
