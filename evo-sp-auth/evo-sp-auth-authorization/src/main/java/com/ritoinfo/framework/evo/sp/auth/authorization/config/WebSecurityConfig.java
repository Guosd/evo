package com.ritoinfo.framework.evo.sp.auth.authorization.config;

import com.ritoinfo.framework.evo.sp.auth.authorization.extend.filter.RequestBodyToUrlQueryStringFilter;
import com.ritoinfo.framework.evo.sp.auth.authorization.extend.mnvc.MobileNumberVerifyCodeAuthenticationProvider;
import com.ritoinfo.framework.evo.sp.auth.extend.LoginUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

/**
 * User: Kyll
 * Date: 2018-12-20 11:07
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final RequestBodyToUrlQueryStringFilter requestBodyToUrlQueryStringFilter;
	private final LoginUserDetailsService loginUserDetailsService;

	@Autowired
	public WebSecurityConfig(RequestBodyToUrlQueryStringFilter requestBodyToUrlQueryStringFilter, LoginUserDetailsService loginUserDetailsService) {
		this.requestBodyToUrlQueryStringFilter = requestBodyToUrlQueryStringFilter;
		this.loginUserDetailsService = loginUserDetailsService;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		MobileNumberVerifyCodeAuthenticationProvider mobileNumberVerifyCodeAuthenticationProvider = new MobileNumberVerifyCodeAuthenticationProvider();
		mobileNumberVerifyCodeAuthenticationProvider.setUserDetailsService(loginUserDetailsService);

		http
				.csrf().disable()
				.addFilterBefore(requestBodyToUrlQueryStringFilter, ChannelProcessingFilter.class)
				.authenticationProvider(mobileNumberVerifyCodeAuthenticationProvider)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
				.requestMatchers().anyRequest()
				.and()
				.formLogin().permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/oauth/*").permitAll();
	}
}
