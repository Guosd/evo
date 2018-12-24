package com.ritoinfo.framework.evo.sp.auth.authorization.config;

import com.ritoinfo.framework.evo.sp.auth.authorization.config.properties.AuthorizationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * User: Kyll
 * Date: 2018-12-21 14:52
 */
@Configuration
public class PersistenceConfig {
	private final AuthorizationProperties authorizationProperties;
	private final DataSource dataSource;

	@Autowired
	public PersistenceConfig(AuthorizationProperties authorizationProperties, DataSource dataSource) {
		this.authorizationProperties = authorizationProperties;
		this.dataSource = dataSource;
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(authorizationProperties.getJwtSigningKey());
		return converter;
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
		return new JdbcTokenStore(dataSource);
	}
}
