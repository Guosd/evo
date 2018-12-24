package com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc;

/**
 * User: Kyll
 * Date: 2018-12-21 16:23
 */
public interface MobileNumberVerifyCodeUserDetailsService {
	MobileNumberVerifyCodeUserDetails loadUserByMobileNumber(String mobileNumber) throws MobileNumberNotFoundException;
}
