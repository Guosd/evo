package com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc;

/**
 * User: Kyll
 * Date: 2018-12-21 16:46
 */
public interface MobileNumberVerifyCodeUserCache {
	MobileNumberVerifyCodeUserDetails getUserFromCache(String var1);

	void putUserInCache(MobileNumberVerifyCodeUserDetails var1);

	void removeUserFromCache(String var1);
}
