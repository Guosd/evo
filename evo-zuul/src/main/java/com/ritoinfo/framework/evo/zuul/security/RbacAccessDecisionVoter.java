package com.ritoinfo.framework.evo.zuul.security;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.sp.auth.extend.LoginAuthenticationToken;
import com.ritoinfo.framework.evo.sp.auth.extend.LoginUser;
import com.ritoinfo.framework.evo.zuul.security.api.RbacApi;
import com.ritoinfo.framework.evo.zuul.security.api.model.RbacDto;
import com.ritoinfo.framework.evo.zuul.security.exception.LoginUserNotExtractedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
	private RbacApi rbacApi;

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
		GrantedAuthority[] grantedAuthorities = authentication.getAuthorities().toArray(new GrantedAuthority[0]);
		if (grantedAuthorities.length == 1 && Const.ROLE_ANONYMOUS.equals(grantedAuthorities[0].toString())) {
			return ACCESS_DENIED;
		}

		RbacDto rbacDto = RbacDto.builder()
				.userId(extractLoginUser(authentication).getId())
				.method(filterInvocation.getHttpRequest().getMethod())
				.uri(filterInvocation.getRequestUrl()).build();

		Boolean apiResult = rbacApi.validate(rbacDto).getData();
		int result = apiResult == null ? ACCESS_ABSTAIN : (apiResult ? ACCESS_GRANTED : ACCESS_DENIED);

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
