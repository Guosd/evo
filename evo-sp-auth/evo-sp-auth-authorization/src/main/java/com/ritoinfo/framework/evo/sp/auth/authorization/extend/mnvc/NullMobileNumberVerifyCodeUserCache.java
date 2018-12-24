package com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc;

/**
 * User: Kyll
 * Date: 2018-12-21 16:47
 */
public class NullMobileNumberVerifyCodeUserCache implements MobileNumberVerifyCodeUserCache {
	public NullMobileNumberVerifyCodeUserCache() {
	}

	public MobileNumberVerifyCodeUserDetails getUserFromCache(String username) {
		return null;
	}

	public void putUserInCache(MobileNumberVerifyCodeUserDetails user) {
	}

	public void removeUserFromCache(String username) {
	}
}
