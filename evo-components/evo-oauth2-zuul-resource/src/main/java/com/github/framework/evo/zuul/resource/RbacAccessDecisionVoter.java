package com.github.framework.evo.zuul.resource;

import com.github.framework.evo.auth.api.IamApi;
import com.github.framework.evo.auth.model.RbacDto;
import com.github.framework.evo.oauth2.extend.LoginAuthenticationToken;
import com.github.framework.evo.oauth2.extend.LoginUser;
import com.github.framework.evo.oauth2.util.OAuth2Util;
import com.github.framework.evo.zuul.resource.exception.LoginUserNotExtractedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * User: Kyll
 * Date: 2018-12-28 18:00
 */
@Slf4j
@Component
public class RbacAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
	@Autowired
	private IamApi iamApi;

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	@Override
	public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {
		if (OAuth2Util.isAnonymous(authentication)) {
			return ACCESS_DENIED;
		}

		RbacDto rbacDto = RbacDto.builder()
				.userId(extractLoginUser(authentication).getId())
				.method(filterInvocation.getHttpRequest().getMethod())
				.uri(filterInvocation.getRequestUrl()).build();

		int result = iamApi.check(rbacDto) ? ACCESS_GRANTED : ACCESS_DENIED;

		log.debug("User Id: {}, Method: {}, Uri: {}, Result: {}", rbacDto.getUserId(), rbacDto.getMethod(), rbacDto.getUri(), result);

		return result;
	}

	private LoginUser extractLoginUser(Authentication authentication) {
		LoginUser loginUser = null;
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
			Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
			if (userAuthentication instanceof LoginAuthenticationToken) {
				LoginAuthenticationToken loginAuthenticationToken = (LoginAuthenticationToken) userAuthentication;
				Object principal = loginAuthenticationToken.getPrincipal();
				if (principal instanceof LoginUser) {
					loginUser = (LoginUser) principal;
				}
			}
		}

		if (loginUser == null) {
			throw new LoginUserNotExtractedException(String.format("Authentication: %s", authentication));
		}

		return loginUser;
	}
}
