package com.ritoinfo.framework.evo.oauth2.util;

import com.ritoinfo.framework.evo.common.Const;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * User: Kyll
 * Date: 2019-01-31 11:50
 */
public class OAuth2Util {
	public static boolean isAnonymous(Authentication authentication) {
		GrantedAuthority[] grantedAuthorities = authentication.getAuthorities().toArray(new GrantedAuthority[0]);
		return grantedAuthorities.length == 1 && Const.ROLE_ANONYMOUS.equals(grantedAuthorities[0].toString());
	}
}
