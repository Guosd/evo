package com.ritoinfo.framework.evo.zuul.config;

import com.ritoinfo.framework.evo.zuul.config.properties.AuthorizationProperties;
import com.ritoinfo.framework.evo.zuul.config.properties.ZuulAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * User: Kyll
 * Date: 2018-12-20 12:58
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private AuthorizationProperties authorizationProperties;
	@Autowired
	private ZuulAuthProperties zuulAuthProperties;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(authorizationProperties.getJwtSigningKey());
		return converter;
	}

	@Bean
	public ResourceServerTokenServices tokenServices() {
		// 配置RemoteTokenServices，用于向AuththorizationServer验证token
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setAccessTokenConverter(accessTokenConverter());
		tokenServices.setCheckTokenEndpointUrl("http://" + authorizationProperties.getServiceId() + "/oauth/check_token");
		tokenServices.setClientId(zuulAuthProperties.getClientId());
		tokenServices.setClientSecret(zuulAuthProperties.getClientSecret());
		return tokenServices;

	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
				.tokenServices(tokenServices())
				.stateless(true);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
				.requestMatchers().anyRequest()
				.and()
				.anonymous()
				.and()
				.authorizeRequests()
				.antMatchers("/user/**").authenticated() // /user/** 端点的访问必须要验证后
				.and()
				.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}
