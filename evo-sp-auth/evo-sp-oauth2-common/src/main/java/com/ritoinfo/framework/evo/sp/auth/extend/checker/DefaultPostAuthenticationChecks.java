package com.ritoinfo.framework.evo.sp.auth.extend.checker;

import com.ritoinfo.framework.evo.sp.auth.extend.LoginUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.CredentialsExpiredException;

/**
 * User: Kyll
 * Date: 2019-01-02 10:51
 */
@Slf4j
public class DefaultPostAuthenticationChecks implements LoginUserDetailsChecker {
	public void check(LoginUserDetails user) {
		if (!user.isCredentialsNonExpired()) {
			log.debug("User account credentials have expired");
			throw new CredentialsExpiredException("User credentials have expired");
		}
	}
}
