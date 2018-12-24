package com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * User: Kyll
 * Date: 2018-12-21 15:51
 */
@Slf4j
public class MobileNumberVerifyCodeAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private MobileNumberVerifyCodeUserCache userCache = new NullMobileNumberVerifyCodeUserCache();
	private boolean forcePrincipalAsString = false;
	protected boolean hideUserNotFoundExceptions = true;
	private MobileNumberVerifyCodeUserDetailsChecker preAuthenticationChecks = new MobileNumberVerifyCodeAuthenticationProvider.DefaultPreAuthenticationChecks();
	private MobileNumberVerifyCodeUserDetailsChecker postAuthenticationChecks = new MobileNumberVerifyCodeAuthenticationProvider.DefaultPostAuthenticationChecks();
	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
	private MobileNumberVerifyCodeUserDetailsService userDetailsService;

	protected void additionalAuthenticationChecks(MobileNumberVerifyCodeUserDetails userDetails, MobileNumberVerifyCodeAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			log.debug("Authentication failed: no credentials provided");
			throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		} else {
			String verifyCode = authentication.getCredentials().toString();
			if (false) {// TODO 与 redis 中的 verifyCode 对比
				log.debug("Authentication failed: verifyCode does not match stored value");
				throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
			}
		}
	}

	public final void afterPropertiesSet() throws Exception {
		Assert.notNull(this.userCache, "A user cache must be set");
		Assert.notNull(this.messages, "A message source must be set");
		this.doAfterPropertiesSet();
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.isInstanceOf(MobileNumberVerifyCodeAuthenticationToken.class, authentication, this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports", "Only MobileNumberVerifyCodeAuthenticationToken is supported"));
		String mobileNumber = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
		boolean cacheWasUsed = true;
		MobileNumberVerifyCodeUserDetails user = this.userCache.getUserFromCache(mobileNumber);
		if (user == null) {
			cacheWasUsed = false;

			try {
				user = this.retrieveUser(mobileNumber, (MobileNumberVerifyCodeAuthenticationToken)authentication);
			} catch (UsernameNotFoundException e) {
				log.debug("User '" + mobileNumber + "' not found");
				if (this.hideUserNotFoundExceptions) {
					throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
				}

				throw e;
			}

			Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
		}

		try {
			this.preAuthenticationChecks.check(user);
			this.additionalAuthenticationChecks(user, (MobileNumberVerifyCodeAuthenticationToken) authentication);
		} catch (AuthenticationException e) {
			if (!cacheWasUsed) {
				throw e;
			}

			cacheWasUsed = false;
			user = this.retrieveUser(mobileNumber, (MobileNumberVerifyCodeAuthenticationToken) authentication);
			this.preAuthenticationChecks.check(user);
			this.additionalAuthenticationChecks(user, (MobileNumberVerifyCodeAuthenticationToken) authentication);
		}

		this.postAuthenticationChecks.check(user);
		if (!cacheWasUsed) {
			this.userCache.putUserInCache(user);
		}

		Object principalToReturn = user;
		if (this.forcePrincipalAsString) {
			principalToReturn = user.getMobileNumber();
		}

		return this.createSuccessAuthentication(principalToReturn, authentication, user);
	}

	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, MobileNumberVerifyCodeUserDetails user) {
		MobileNumberVerifyCodeAuthenticationToken result = new MobileNumberVerifyCodeAuthenticationToken(principal, authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
		result.setDetails(authentication.getDetails());
		return result;
	}

	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService, "A MobileNumberVerifyCodeUserDetailsService must be set");
	}

	public MobileNumberVerifyCodeUserCache getUserCache() {
		return this.userCache;
	}

	public boolean isForcePrincipalAsString() {
		return this.forcePrincipalAsString;
	}

	public boolean isHideUserNotFoundExceptions() {
		return this.hideUserNotFoundExceptions;
	}

	protected final MobileNumberVerifyCodeUserDetails retrieveUser(String mobileNumber, MobileNumberVerifyCodeAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		try {
			MobileNumberVerifyCodeUserDetails loadedUser = this.getUserDetailsService().loadUserByMobileNumber(mobileNumber);
			if (loadedUser == null) {
				throw new InternalAuthenticationServiceException("MobileNumberVerifyCodeUserDetailsService returned null, which is an interface contract violation");
			} else {
				return loadedUser;
			}
		} catch (MobileNumberNotFoundException | InternalAuthenticationServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalAuthenticationServiceException(e.getMessage(), e);
		}
	}

	public void setUserDetailsService(MobileNumberVerifyCodeUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected MobileNumberVerifyCodeUserDetailsService getUserDetailsService() {
		return this.userDetailsService;
	}

	public void setForcePrincipalAsString(boolean forcePrincipalAsString) {
		this.forcePrincipalAsString = forcePrincipalAsString;
	}

	public void setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions) {
		this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}

	public void setUserCache(MobileNumberVerifyCodeUserCache userCache) {
		this.userCache = userCache;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MobileNumberVerifyCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	protected MobileNumberVerifyCodeUserDetailsChecker getPreAuthenticationChecks() {
		return this.preAuthenticationChecks;
	}

	public void setPreAuthenticationChecks(MobileNumberVerifyCodeUserDetailsChecker preAuthenticationChecks) {
		this.preAuthenticationChecks = preAuthenticationChecks;
	}

	protected MobileNumberVerifyCodeUserDetailsChecker getPostAuthenticationChecks() {
		return this.postAuthenticationChecks;
	}

	public void setPostAuthenticationChecks(MobileNumberVerifyCodeUserDetailsChecker postAuthenticationChecks) {
		this.postAuthenticationChecks = postAuthenticationChecks;
	}

	public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
		this.authoritiesMapper = authoritiesMapper;
	}

	private class DefaultPostAuthenticationChecks implements MobileNumberVerifyCodeUserDetailsChecker {
		private DefaultPostAuthenticationChecks() {
		}

		public void check(MobileNumberVerifyCodeUserDetails user) {
			if (!user.isCredentialsNonExpired()) {
				MobileNumberVerifyCodeAuthenticationProvider.log.debug("User account credentials have expired");
				throw new CredentialsExpiredException(MobileNumberVerifyCodeAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
			}
		}
	}

	private class DefaultPreAuthenticationChecks implements MobileNumberVerifyCodeUserDetailsChecker {
		private DefaultPreAuthenticationChecks() {
		}

		public void check(MobileNumberVerifyCodeUserDetails user) {
			if (!user.isAccountNonLocked()) {
				MobileNumberVerifyCodeAuthenticationProvider.log.debug("User account is locked");
				throw new LockedException(MobileNumberVerifyCodeAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
			} else if (!user.isEnabled()) {
				MobileNumberVerifyCodeAuthenticationProvider.log.debug("User account is disabled");
				throw new DisabledException(MobileNumberVerifyCodeAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
			} else if (!user.isAccountNonExpired()) {
				MobileNumberVerifyCodeAuthenticationProvider.log.debug("User account is expired");
				throw new AccountExpiredException(MobileNumberVerifyCodeAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
			}
		}
	}
}
