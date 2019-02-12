package com.github.framework.evo.oauth2.extend;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * User: Kyll
 * Date: 2019-01-02 09:40
 */
public interface LoginUserDetails extends Serializable {
	Collection<? extends GrantedAuthority> getAuthorities();

	String getId();

	String getUsername();

	String getEmail();

	String getMobileNumber();

	boolean isAccountNonExpired();

	boolean isAccountNonLocked();

	boolean isCredentialsNonExpired();

	boolean isEnabled();
}
