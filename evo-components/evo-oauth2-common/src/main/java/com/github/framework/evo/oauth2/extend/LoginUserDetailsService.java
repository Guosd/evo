package com.github.framework.evo.oauth2.extend;

import com.github.framework.evo.oauth2.extend.exception.MobileNumberNotFoundException;

/**
 * User: Kyll
 * Date: 2018-12-21 16:23
 */
public interface LoginUserDetailsService {
	LoginUserDetails loadUserByMobileNumber(String mobileNumber) throws MobileNumberNotFoundException;
}
