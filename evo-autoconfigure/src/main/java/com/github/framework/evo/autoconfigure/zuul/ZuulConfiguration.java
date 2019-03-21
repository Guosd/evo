package com.github.framework.evo.autoconfigure.zuul;

import com.github.framework.evo.zuul.dynamicroute.DynamicRouteLocator;
import com.github.framework.evo.zuul.dynamicroute.dao.EvoZuulRouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.netflix.zuul.ZuulFilterInitializer;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * User: Kyll
 * Date: 2019-03-18 15:18
 */
@Configuration
@ConditionalOnClass(ZuulFilterInitializer.class)
public class ZuulConfiguration {
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private DiscoveryClient discovery;
	@Autowired
	protected ZuulProperties zuulProperties;
	@Autowired
	private ServiceRouteMapper serviceRouteMapper;
	@Autowired(required = false)
	private Registration registration;
	@Autowired
	private EvoZuulRouteDao evoZuulRouteDao;

	@Bean
	public DiscoveryClientRouteLocator discoveryRouteLocator() {
		return new DynamicRouteLocator("", this.discovery, this.zuulProperties, this.serviceRouteMapper, this.registration, this.evoZuulRouteDao);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setMaxAge(18000L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
