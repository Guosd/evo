package com.ritoinfo.framework.evo.sp.auth.extend.cache;

import com.ritoinfo.framework.evo.sp.auth.extend.LoginUserDetails;

/**
 * User: Kyll
 * Date: 2018-12-21 16:46
 */
public interface LoginUserCache {
	LoginUserDetails getUserFromCache(String var1);

	void putUserInCache(LoginUserDetails var1);

	void removeUserFromCache(String var1);
}
