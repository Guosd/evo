package com.ritoinfo.framework.evo.sp.auth.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * User: Kyll
 * Date: 2018-12-21 14:55
 */
@Configuration
public class AuthorizationConfig {
	private final JwtAccessTokenConverter accessTokenConverter;
	private final TokenStore tokenStore;
	private final ClientDetailsService clientDetailsService;

	@Autowired
	public AuthorizationConfig(JwtAccessTokenConverter accessTokenConverter, TokenStore tokenStore, ClientDetailsService clientDetailsService) {
		this.accessTokenConverter = accessTokenConverter;
		this.tokenStore = tokenStore;
		this.clientDetailsService = clientDetailsService;
	}

	@Bean
	public AuthorizationServerTokenServices authorizationServerTokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore);
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setReuseRefreshToken(false);
		tokenServices.setClientDetailsService(clientDetailsService);
		tokenServices.setTokenEnhancer(accessTokenConverter);
		return tokenServices;
	}

	@Bean
	public OAuth2RequestFactory requestFactory() {
		return new DefaultOAuth2RequestFactory(clientDetailsService);
	}
}
