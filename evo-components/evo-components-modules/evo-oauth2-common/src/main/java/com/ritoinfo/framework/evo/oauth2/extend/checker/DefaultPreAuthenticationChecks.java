package com.ritoinfo.framework.evo.oauth2.extend.checker;

import com.ritoinfo.framework.evo.oauth2.extend.LoginUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;

/**
 * User: Kyll
 * Date: 2019-01-02 10:51
 */
@Slf4j
public class DefaultPreAuthenticationChecks implements LoginUserDetailsChecker {
	public void check(LoginUserDetails user) {
		if (!user.isAccountNonLocked()) {
			log.debug("User account is locked");
			throw new LockedException("User account is locked");
		} else if (!user.isEnabled()) {
			log.debug("User account is disabled");
			throw new DisabledException("User is disabled");
		} else if (!user.isAccountNonExpired()) {
			log.debug("User account is expired");
			throw new AccountExpiredException("User account has expired");
		}
	}
}
