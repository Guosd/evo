package com.ritoinfo.framework.evo.autoconfigure.oauth2;

import com.ritoinfo.framework.evo.autoconfigure.auth.AuthProperties;
import com.ritoinfo.framework.evo.autoconfigure.zuul.ZuulProperties;
import com.ritoinfo.framework.evo.zuul.filter.SecurityContextHolderFilter;
import com.ritoinfo.framework.evo.zuul.resource.LoginAccessTokenConverter;
import com.ritoinfo.framework.evo.zuul.resource.PathExcludeAccessDecisionVoter;
import com.ritoinfo.framework.evo.zuul.resource.RbacAccessDecisionVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * User: Kyll
 * Date: 2018-12-20 12:58
 */
@EnableResourceServer
@EnableConfigurationProperties({AuthProperties.class, ZuulProperties.class})
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private AuthProperties authProperties;
	@Autowired
	private ZuulProperties zuulProperties;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PathExcludeAccessDecisionVoter pathExcludeAccessDecisionVoter;
	@Autowired
	private RbacAccessDecisionVoter rbacAccessDecisionVoter;
	@Autowired
	private LoginAccessTokenConverter loginAccessTokenConverter;

	@Bean
	public ResourceServerTokenServices tokenServices() {
		// 配置RemoteTokenServices，用于向AuththorizationServer验证token
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setRestTemplate(restTemplate);
		tokenServices.setCheckTokenEndpointUrl("http://" + authProperties.getAuthorization().getServiceId().toUpperCase() + "/oauth/check_token");

		ZuulProperties.Auth.Client client = zuulProperties.getAuth().getClient();
		tokenServices.setClientId(client.getId());
		tokenServices.setClientSecret(client.getSecret());
		tokenServices.setAccessTokenConverter(loginAccessTokenConverter);
		return tokenServices;
	}

	@Bean
	public AccessDecisionManager accessDecisionManager() {
		return new AffirmativeBased(Arrays.asList(pathExcludeAccessDecisionVoter, rbacAccessDecisionVoter));
	}

	@Bean
	public SecurityContextHolderFilter securityContextHolderFilter() {
		return new SecurityContextHolderFilter();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
				.tokenServices(tokenServices())
				.stateless(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		 		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
				.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
				.and()
				.authorizeRequests().anyRequest().authenticated().accessDecisionManager(accessDecisionManager());
	}
}
