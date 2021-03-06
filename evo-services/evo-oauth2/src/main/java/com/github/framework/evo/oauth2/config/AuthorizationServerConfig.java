package com.github.framework.evo.oauth2.config;

import com.github.framework.evo.data.redis.RedisKeyGenerator;
import com.github.framework.evo.auth.config.JwtConfig;
import com.github.framework.evo.oauth2.extend.mnvc.MobileNumberVerifyCodeTokenGranter;
import com.github.framework.evo.oauth2.extend.token.LoginUserAccessTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
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
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-12-20 10:56
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	private final DataSource dataSource;
	private final RedisConnectionFactory redisConnectionFactory;
	private final RedisKeyGenerator redisKeyGenerator;
	private final JwtConfig jwtConfig;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthorizationServerConfig(DataSource dataSource, RedisConnectionFactory redisConnectionFactory, RedisKeyGenerator redisKeyGenerator, JwtConfig jwtConfig, AuthenticationManager authenticationManager) {
		this.dataSource = dataSource;
		this.redisConnectionFactory = redisConnectionFactory;
		this.redisKeyGenerator = redisKeyGenerator;
		this.jwtConfig = jwtConfig;
		this.authenticationManager = authenticationManager;
	}

	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

	@Bean
	public ClientDetailsService clientDetailsService() {
		return new JdbcClientDetailsService(dataSource);
	}

	@Bean
	public TokenStore tokenStore() {
		String prefix = redisKeyGenerator.generate(RedisTokenStore.class, "", "");

		RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		redisTokenStore.setPrefix(prefix.substring(0, prefix.length() - 1));
		return redisTokenStore;
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(jwtConfig.getSigningKey());
		return converter;
	}

	@Bean
	public LoginUserAccessTokenConverter userAccessTokenConverter() {
		return new LoginUserAccessTokenConverter();
	}

	@Bean
	public AuthorizationServerTokenServices authorizationServerTokenServices() {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter(), userAccessTokenConverter()));

		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setReuseRefreshToken(false);
		tokenServices.setClientDetailsService(clientDetailsService());
		tokenServices.setTokenEnhancer(tokenEnhancerChain);
		return tokenServices;
	}

	@Bean
	public OAuth2RequestFactory requestFactory() {
		return new DefaultOAuth2RequestFactory(clientDetailsService());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security
				.tokenKeyAccess("permitAll()") // 允许所有人请求token
				.checkTokenAccess("isAuthenticated()") // 已验证的用户才能请求check_token端点
				.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints
				.authenticationManager(authenticationManager)
				.authorizationCodeServices(authorizationCodeServices())
				.tokenStore(tokenStore())
				.reuseRefreshTokens(false);

		List<TokenGranter> tokenGranters = new ArrayList<>();
		tokenGranters.add(new AuthorizationCodeTokenGranter(authorizationServerTokenServices(), authorizationCodeServices(), clientDetailsService(), requestFactory()));
		tokenGranters.add(new RefreshTokenGranter(authorizationServerTokenServices(), clientDetailsService(), requestFactory()));
		tokenGranters.add(new ImplicitTokenGranter(authorizationServerTokenServices(), clientDetailsService(), requestFactory()));
		tokenGranters.add(new ClientCredentialsTokenGranter(authorizationServerTokenServices(), clientDetailsService(), requestFactory()));
		tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, authorizationServerTokenServices(), clientDetailsService(), requestFactory()));
		tokenGranters.add(new MobileNumberVerifyCodeTokenGranter(authenticationManager, authorizationServerTokenServices(), clientDetailsService(), requestFactory()));

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
