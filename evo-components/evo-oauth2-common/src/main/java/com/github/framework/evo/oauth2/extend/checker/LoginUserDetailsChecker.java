package com.github.framework.evo.oauth2.extend.checker;

import com.github.framework.evo.oauth2.extend.LoginUserDetails;

/**
 * User: Kyll
 * Date: 2018-12-21 16:52
 */
public interface LoginUserDetailsChecker {
	void check(LoginUserDetails user);
}
