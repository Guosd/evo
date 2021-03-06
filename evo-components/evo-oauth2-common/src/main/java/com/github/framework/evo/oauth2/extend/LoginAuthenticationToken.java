package com.github.framework.evo.oauth2.extend;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * User: Kyll
 * Date: 2018-12-21 10:55
 */
public class LoginAuthenticationToken extends AbstractAuthenticationToken {
	@Getter private final Object principal;
	@Getter private Object credentials;

	public LoginAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		this.setAuthenticated(false);
	}

	public LoginAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) {
		if (isAuthenticated) {
			throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		} else {
			super.setAuthenticated(false);
		}
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.credentials = null;
	}

	@Override
	public String getName() {
		if (this.getPrincipal() instanceof LoginUserDetails) {
			return ((LoginUserDetails) this.getPrincipal()).getUsername();
		} else {
			return this.getPrincipal() == null ? "" : this.getPrincipal().toString();
		}
	}
}
