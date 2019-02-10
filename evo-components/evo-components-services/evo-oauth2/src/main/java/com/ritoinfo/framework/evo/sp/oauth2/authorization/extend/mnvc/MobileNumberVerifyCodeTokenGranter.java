package com.ritoinfo.framework.evo.sp.oauth2.authorization.extend.mnvc;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-12-21 15:18
 */
public class MobileNumberVerifyCodeTokenGranter extends AbstractTokenGranter {
	private static final String GRANT_TYPE = "mnvc";
	private final AuthenticationManager authenticationManager;

	public MobileNumberVerifyCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
		this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
	}

	protected MobileNumberVerifyCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
		super(tokenServices, clientDetailsService, requestFactory, grantType);
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
		Map<String, String> parameters = tokenRequest.getRequestParameters();
		String mobileNumber = parameters.get("mobileNumber");
		String verifyCode = parameters.get("verifyCode");

		Authentication authentication = new MobileNumberVerifyCodeAuthenticationToken(mobileNumber, verifyCode);
		((MobileNumberVerifyCodeAuthenticationToken) authentication).setDetails(parameters);

		try {
			authentication = this.authenticationManager.authenticate(authentication);
		} catch (AccountStatusException | BadCredentialsException e) {
			throw new InvalidGrantException(e.getMessage());
		}

		if (authentication == null || !authentication.isAuthenticated()) {
			throw new InvalidGrantException("Could not authenticate user: " + mobileNumber);
		}

		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
		return new OAuth2Authentication(storedOAuth2Request, authentication);
	}
}
