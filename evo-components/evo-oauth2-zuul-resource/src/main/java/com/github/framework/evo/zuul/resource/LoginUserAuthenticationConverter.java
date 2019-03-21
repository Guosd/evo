package com.github.framework.evo.zuul.resource;

import com.github.framework.evo.oauth2.extend.LoginAuthenticationToken;
import com.github.framework.evo.oauth2.extend.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-01-02 09:09
 */
@Slf4j
@Component
public class LoginUserAuthenticationConverter implements UserAuthenticationConverter {
	private Collection<? extends GrantedAuthority> defaultAuthorities;

	public void setDefaultAuthorities(String[] defaultAuthorities) {
		this.defaultAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaDelimitedString(defaultAuthorities));
	}

	@Override
	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put(USERNAME, authentication.getName());
		if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
			response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}

		return response;
	}

	@Override
	public Authentication extractAuthentication(Map<String, ?> map) {
		return new LoginAuthenticationToken(LoginUser.builder()
				.id((String) map.get("id"))
				.name((String) map.get("name"))
				.code((String) map.get("code"))
				.username((String) map.get("username"))
				.email((String) map.get("email"))
				.mobileNumber((String) map.get("mobileNumber"))
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true)
				.build(), "N/A", this.getAuthorities(map));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
		if (!map.containsKey(AUTHORITIES)) {
			return this.defaultAuthorities;
		} else {
			Object authorities = map.get(AUTHORITIES);
			if (authorities instanceof String) {
				return AuthorityUtils.commaSeparatedStringToAuthorityList((String)authorities);
			} else if (authorities instanceof Collection) {
				return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection)authorities));
			} else {
				throw new IllegalArgumentException("Authorities must be either a String or a Collection");
			}
		}
	}
}
