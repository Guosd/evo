package com.ritoinfo.framework.evo.sp.auth.extend.cache;

import com.ritoinfo.framework.evo.sp.auth.extend.LoginUserDetails;

/**
 * User: Kyll
 * Date: 2018-12-21 16:47
 */
public class NullLoginUserCache implements LoginUserCache {
	public LoginUserDetails getUserFromCache(String username) {
		return null;
	}

	public void putUserInCache(LoginUserDetails user) {
		// ignore
	}

	public void removeUserFromCache(String username) {
		// ignore
	}
}