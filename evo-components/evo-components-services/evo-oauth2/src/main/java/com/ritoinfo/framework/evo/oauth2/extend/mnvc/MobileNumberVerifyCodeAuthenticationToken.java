package com.ritoinfo.framework.evo.oauth2.extend.mnvc;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * User: Kyll
 * Date: 2018-12-21 10:55
 */
public class MobileNumberVerifyCodeAuthenticationToken extends AbstractAuthenticationToken {
	@Getter private final String principal;
	@Getter private String credentials;

	public MobileNumberVerifyCodeAuthenticationToken(String principal, String credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		this.setAuthenticated(false);
	}

	public MobileNumberVerifyCodeAuthenticationToken(String principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
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
		return this.getPrincipal();
	}
}
