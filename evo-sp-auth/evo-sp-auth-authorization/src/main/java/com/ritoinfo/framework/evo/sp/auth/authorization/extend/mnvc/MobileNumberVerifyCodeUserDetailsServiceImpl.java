package com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc;

import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsCondition;
import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.UserDetailsBizz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * User: Kyll
 * Date: 2018-12-20 11:31
 */
@Component
public class MobileNumberVerifyCodeUserDetailsServiceImpl implements MobileNumberVerifyCodeUserDetailsService {
	@Autowired
	private UserDetailsBizz userDetailsBizz;

	@Override
	public MobileNumberVerifyCodeUserDetails loadUserByMobileNumber(String mobileNumber) throws MobileNumberNotFoundException {
		UserDetailsDto userDetailsDto = userDetailsBizz.getByMobileNumber(UserDetailsCondition.builder().mobileNumber(mobileNumber).build());

		if (null == userDetailsDto) {
			throw new MobileNumberNotFoundException(mobileNumber);
		}

		return new MobileNumberVerifyCodeUser(userDetailsDto.getMobileNumber(), new ArrayList<>());
	}
}
