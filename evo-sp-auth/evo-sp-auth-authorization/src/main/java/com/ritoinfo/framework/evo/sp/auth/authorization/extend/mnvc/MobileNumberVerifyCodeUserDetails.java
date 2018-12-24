package com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * User: Kyll
 * Date: 2018-12-21 16:25
 */
public interface MobileNumberVerifyCodeUserDetails extends Serializable {
	Collection<? extends GrantedAuthority> getAuthorities();

	String getMobileNumber();

	boolean isAccountNonExpired();

	boolean isAccountNonLocked();

	boolean isCredentialsNonExpired();

	boolean isEnabled();
}
