package com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * User: Kyll
 * Date: 2018-12-21 16:28
 */
@Slf4j
public class MobileNumberVerifyCodeUser implements MobileNumberVerifyCodeUserDetails, CredentialsContainer {
	@Getter private final String mobileNumber;
	@Getter private final Set<GrantedAuthority> authorities;
	@Getter private final boolean accountNonExpired;
	@Getter private final boolean accountNonLocked;
	@Getter private final boolean credentialsNonExpired;
	@Getter private final boolean enabled;

	public MobileNumberVerifyCodeUser(String mobileNumber, Collection<? extends GrantedAuthority> authorities) {
		this(mobileNumber, true, true, true, true, authorities);
	}

	public MobileNumberVerifyCodeUser(String mobileNumber, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		if (mobileNumber != null && !"".equals(mobileNumber)) {
			this.mobileNumber = mobileNumber;
			this.enabled = enabled;
			this.accountNonExpired = accountNonExpired;
			this.credentialsNonExpired = credentialsNonExpired;
			this.accountNonLocked = accountNonLocked;
			this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
		} else {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}
	}

	public void eraseCredentials() {
	}

	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new MobileNumberVerifyCodeUser.AuthorityComparator());
		for (GrantedAuthority grantedAuthority : sortedAuthorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	public boolean equals(Object rhs) {
		return rhs instanceof User && this.mobileNumber.equals(((MobileNumberVerifyCodeUser) rhs).mobileNumber);
	}

	public int hashCode() {
		return this.mobileNumber.hashCode();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("MobileNumber: ").append(this.mobileNumber).append("; ");
		sb.append("Enabled: ").append(this.enabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
		if (!this.authorities.isEmpty()) {
			sb.append("Granted Authorities: ");
			boolean first = true;
			for (GrantedAuthority auth : this.authorities) {
				if (!first) {
					sb.append(",");
				}

				first = false;
				sb.append(auth);
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}

	public static MobileNumberVerifyCodeUser.UserBuilder withMobileNumber(String mobileNumber) {
		return builder().mobileNumber(mobileNumber);
	}

	public static MobileNumberVerifyCodeUser.UserBuilder builder() {
		return new MobileNumberVerifyCodeUser.UserBuilder();
	}

	public static MobileNumberVerifyCodeUser.UserBuilder withUserDetails(MobileNumberVerifyCodeUserDetails userDetails) {
		return withMobileNumber(userDetails.getMobileNumber()).accountExpired(!userDetails.isAccountNonExpired()).accountLocked(!userDetails.isAccountNonLocked()).authorities(userDetails.getAuthorities()).credentialsExpired(!userDetails.isCredentialsNonExpired()).disabled(!userDetails.isEnabled());
	}

	public static class UserBuilder {
		private String mobileNumber;
		private List<GrantedAuthority> authorities;
		private boolean accountExpired;
		private boolean accountLocked;
		private boolean credentialsExpired;
		private boolean disabled;

		private UserBuilder() {
		}

		public MobileNumberVerifyCodeUser.UserBuilder mobileNumber(String mobileNumber) {
			Assert.notNull(mobileNumber, "mobileNumber cannot be null");
			this.mobileNumber = mobileNumber;
			return this;
		}

		public MobileNumberVerifyCodeUser.UserBuilder roles(String... roles) {
			List<GrantedAuthority> authorities = new ArrayList(roles.length);

			for (String role : roles) {
				Assert.isTrue(!role.startsWith("ROLE_"), role + " cannot start with ROLE_ (it is automatically added)");
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			}

			return this.authorities(authorities);
		}

		public MobileNumberVerifyCodeUser.UserBuilder authorities(GrantedAuthority... authorities) {
			return this.authorities(Arrays.asList(authorities));
		}

		public MobileNumberVerifyCodeUser.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = new ArrayList<>(authorities);
			return this;
		}

		public MobileNumberVerifyCodeUser.UserBuilder authorities(String... authorities) {
			return this.authorities(AuthorityUtils.createAuthorityList(authorities));
		}

		public MobileNumberVerifyCodeUser.UserBuilder accountExpired(boolean accountExpired) {
			this.accountExpired = accountExpired;
			return this;
		}

		public MobileNumberVerifyCodeUser.UserBuilder accountLocked(boolean accountLocked) {
			this.accountLocked = accountLocked;
			return this;
		}

		public MobileNumberVerifyCodeUser.UserBuilder credentialsExpired(boolean credentialsExpired) {
			this.credentialsExpired = credentialsExpired;
			return this;
		}

		public MobileNumberVerifyCodeUser.UserBuilder disabled(boolean disabled) {
			this.disabled = disabled;
			return this;
		}

		public MobileNumberVerifyCodeUserDetails build() {
			return new MobileNumberVerifyCodeUser(this.mobileNumber, !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
		}
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
