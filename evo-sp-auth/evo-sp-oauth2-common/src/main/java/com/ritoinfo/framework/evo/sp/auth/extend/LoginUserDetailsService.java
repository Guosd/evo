package com.ritoinfo.framework.evo.sp.auth.extend;

import com.ritoinfo.framework.evo.sp.auth.extend.exception.MobileNumberNotFoundException;

/**
 * User: Kyll
 * Date: 2018-12-21 16:23
 */
public interface LoginUserDetailsService {
	LoginUserDetails loadUserByMobileNumber(String mobileNumber) throws MobileNumberNotFoundException;
}
