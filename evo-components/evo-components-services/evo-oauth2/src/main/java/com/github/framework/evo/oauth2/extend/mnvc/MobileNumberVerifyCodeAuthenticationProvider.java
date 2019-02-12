package com.github.framework.evo.oauth2.extend.mnvc;

import com.github.framework.evo.auth.bizz.VerifyCodeBizz;
import com.github.framework.evo.oauth2.extend.LoginAuthenticationToken;
import com.github.framework.evo.oauth2.extend.LoginUserDetails;
import com.github.framework.evo.oauth2.extend.LoginUserDetailsService;
import com.github.framework.evo.oauth2.extend.cache.LoginUserCache;
import com.github.framework.evo.oauth2.extend.cache.NullLoginUserCache;
import com.github.framework.evo.oauth2.extend.checker.DefaultPostAuthenticationChecks;
import com.github.framework.evo.oauth2.extend.checker.DefaultPreAuthenticationChecks;
import com.github.framework.evo.oauth2.extend.checker.LoginUserDetailsChecker;
import com.github.framework.evo.oauth2.extend.exception.MobileNumberNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
	private LoginUserCache userCache = new NullLoginUserCache();
	private boolean forcePrincipalAsString = false;
	protected boolean hideUserNotFoundExceptions = true;
	private LoginUserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
	private LoginUserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();
	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
	private LoginUserDetailsService userDetailsService;
	private VerifyCodeBizz verifyCodeBizz;

	protected void additionalAuthenticationChecks(LoginUserDetails userDetails, MobileNumberVerifyCodeAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			log.debug("Authentication failed: no credentials provided");
			throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		} else {
			if (!verifyCodeBizz.check(userDetails.getMobileNumber(), authentication.getCredentials())) {
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
		LoginUserDetails user = this.userCache.getUserFromCache(mobileNumber);
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

	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, LoginUserDetails user) {
		LoginAuthenticationToken result = new LoginAuthenticationToken(principal, authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
		result.setDetails(authentication.getDetails());
		return result;
	}

	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService, "A MobileNumberVerifyCodeUserDetailsService must be set");
	}

	public LoginUserCache getUserCache() {
		return this.userCache;
	}

	public boolean isForcePrincipalAsString() {
		return this.forcePrincipalAsString;
	}

	public boolean isHideUserNotFoundExceptions() {
		return this.hideUserNotFoundExceptions;
	}

	protected final LoginUserDetails retrieveUser(String mobileNumber, MobileNumberVerifyCodeAuthenticationToken mobileNumberVerifyCodeAuthenticationToken) throws AuthenticationException {
		try {
			LoginUserDetails loadedUser = this.getUserDetailsService().loadUserByMobileNumber(mobileNumber);
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

	public void setUserDetailsService(LoginUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected LoginUserDetailsService getUserDetailsService() {
		return this.userDetailsService;
	}

	public void setVerifyCodeBizz(VerifyCodeBizz verifyCodeBizz) {
		this.verifyCodeBizz = verifyCodeBizz;
	}

	protected VerifyCodeBizz getVerifyCodeBizz() {
		return this.verifyCodeBizz;
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

	public void setUserCache(LoginUserCache userCache) {
		this.userCache = userCache;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MobileNumberVerifyCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	protected LoginUserDetailsChecker getPreAuthenticationChecks() {
		return this.preAuthenticationChecks;
	}

	public void setPreAuthenticationChecks(LoginUserDetailsChecker preAuthenticationChecks) {
		this.preAuthenticationChecks = preAuthenticationChecks;
	}

	protected LoginUserDetailsChecker getPostAuthenticationChecks() {
		return this.postAuthenticationChecks;
	}

	public void setPostAuthenticationChecks(LoginUserDetailsChecker postAuthenticationChecks) {
		this.postAuthenticationChecks = postAuthenticationChecks;
	}

	public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
		this.authoritiesMapper = authoritiesMapper;
	}
}
