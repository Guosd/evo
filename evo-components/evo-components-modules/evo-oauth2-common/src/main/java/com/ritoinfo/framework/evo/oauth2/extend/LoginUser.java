package com.ritoinfo.framework.evo.oauth2.extend;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * User: Kyll
 * Date: 2018-12-21 16:28
 */
@Slf4j
@Builder
@EqualsAndHashCode
@ToString
public class LoginUser implements UserDetails, LoginUserDetails, CredentialsContainer {
	@Getter private final String id;
	@Getter private String name;
	@Getter private String code;
	@Getter private String username;
	@Getter private String password;
	@Getter private String email;
	@Getter private final String mobileNumber;
	@Getter private final Set<GrantedAuthority> authorities;
	@Getter private final boolean accountNonExpired;
	@Getter private final boolean accountNonLocked;
	@Getter private final boolean credentialsNonExpired;
	@Getter private final boolean enabled;

	public void eraseCredentials() {
		// ignore
	}

	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		private AuthorityComparator() {
		}

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			if (g2.getAuthority() == null) {
				return -1;
			} else {
				return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
			}
		}
	}
}
