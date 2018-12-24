package com.ritoinfo.framework.evo.sp.auth.authorization.config;

import com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc.MobileNumberVerifyCodeTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-12-20 10:56
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	@Autowired
	private AuthorizationCodeServices authorizationCodeServices;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private TokenStore tokenStore;
	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;
	@Autowired
	private OAuth2RequestFactory requestFactory;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
				.tokenKeyAccess("permitAll()") // 允许所有人请求token
				.checkTokenAccess("isAuthenticated()") // 已验证的用户才能请求check_token端点
				.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.accessTokenConverter(accessTokenConverter)
				.authenticationManager(authenticationManager)
				.authorizationCodeServices(authorizationCodeServices)
				.tokenEnhancer(accessTokenConverter)
				.tokenStore(tokenStore)
				.reuseRefreshTokens(false);

		List<TokenGranter> tokenGranters = new ArrayList<>();
		tokenGranters.add(new AuthorizationCodeTokenGranter(authorizationServerTokenServices, authorizationCodeServices, clientDetailsService, requestFactory));
		tokenGranters.add(new RefreshTokenGranter(authorizationServerTokenServices, clientDetailsService, requestFactory));
		tokenGranters.add(new ImplicitTokenGranter(authorizationServerTokenServices, clientDetailsService, requestFactory));
		tokenGranters.add(new ClientCredentialsTokenGranter(authorizationServerTokenServices, clientDetailsService, requestFactory));
		tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, authorizationServerTokenServices, clientDetailsService, requestFactory));
		tokenGranters.add(new MobileNumberVerifyCodeTokenGranter(authenticationManager, authorizationServerTokenServices, clientDetailsService, requestFactory));

		endpoints.tokenGranter(new TokenGranter() {
			private CompositeTokenGranter delegate;

			@Override
			public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
				if (this.delegate == null) {
					this.delegate = new CompositeTokenGranter(tokenGranters);
				}

				return this.delegate.grant(grantType, tokenRequest);
			}
		});
	}
}
