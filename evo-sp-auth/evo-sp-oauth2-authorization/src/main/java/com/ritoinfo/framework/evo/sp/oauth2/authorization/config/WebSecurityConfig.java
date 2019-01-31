package com.ritoinfo.framework.evo.sp.oauth2.authorization.config;

import com.ritoinfo.framework.evo.sp.auth.bizz.VerifyCodeBizz;
import com.ritoinfo.framework.evo.sp.oauth2.authorization.extend.filter.RequestBodyToUrlQueryStringFilter;
import com.ritoinfo.framework.evo.sp.oauth2.authorization.extend.mnvc.MobileNumberVerifyCodeAuthenticationProvider;
import com.ritoinfo.framework.evo.sp.oauth2.extend.LoginUserDetailsService;
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
	private final VerifyCodeBizz verifyCodeBizz;

	@Autowired
	public WebSecurityConfig(RequestBodyToUrlQueryStringFilter requestBodyToUrlQueryStringFilter, LoginUserDetailsService loginUserDetailsService, VerifyCodeBizz verifyCodeBizz) {
		this.requestBodyToUrlQueryStringFilter = requestBodyToUrlQueryStringFilter;
		this.loginUserDetailsService = loginUserDetailsService;
		this.verifyCodeBizz = verifyCodeBizz;
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
		mobileNumberVerifyCodeAuthenticationProvider.setVerifyCodeBizz(verifyCodeBizz);

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
