package com.ritoinfo.framework.evo.autoconfigure.auth;

import com.ritoinfo.framework.evo.sp.auth.config.AuthorizationConfig;
import com.ritoinfo.framework.evo.sp.auth.config.JwtConfig;
import com.ritoinfo.framework.evo.sp.auth.config.PathConfig;
import com.ritoinfo.framework.evo.sp.auth.config.RbacConfig;
import com.ritoinfo.framework.evo.sp.auth.config.UserDetailsConfig;
import com.ritoinfo.framework.evo.sp.auth.config.VerifyCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@EnableConfigurationProperties(AuthProperties.class)
@Configuration
public class AuthConfiguration {
	private final AuthProperties authProperties;

	@Autowired
	public AuthConfiguration(AuthProperties authProperties) {
		this.authProperties = authProperties;
	}

	@Bean
	public AuthorizationConfig authorizationConfig() {
		return getAuthorizationConfig(authProperties.getAuthorization());
	}

	@Bean
	public JwtConfig jwtConfig() {
		return getJwtConfig(authProperties.getJwt());
	}

	@Bean
	public PathConfig pathConfig() {
		return getPathConfig(authProperties.getPath());
	}

	@Bean
	public RbacConfig rbacConfig() {
		return getRbacConfig(authProperties.getRbac());
	}

	@Bean
	public UserDetailsConfig userDetailsConfig() {
		return getUserDetailsConfig(authProperties.getUserDetails());
	}

	@Bean
	public VerifyCodeConfig verifyCodeConfig() {
		return getVerifyCodeConfig(authProperties.getVerifyCode());
	}


	private AuthorizationConfig getAuthorizationConfig(AuthProperties.Authorization authorization) {
		return AuthorizationConfig.builder().serviceId(authorization.getServiceId()).build();
	}

	private JwtConfig getJwtConfig(AuthProperties.Jwt jwt) {
		return JwtConfig.builder()
				.issuer(jwt.getIssuer())
				.signingKey(jwt.getSigningKey())
				.expirationTime(jwt.getExpirationTime())
				.refreshExpirationTime(jwt.getRefreshExpirationTime())
				.oldExpirationTime(jwt.getOldExpirationTime()).build();
	}

	private PathConfig getPathConfig(AuthProperties.Path path) {
		return PathConfig.builder().login(path.getLogin()).excludes(path.getExcludes()).build();
	}

	private RbacConfig getRbacConfig(AuthProperties.Rbac rbac) {
		return RbacConfig.builder().serviceId(rbac.getServiceId()).uri(rbac.getUri()).build();
	}

	private UserDetailsConfig getUserDetailsConfig(AuthProperties.UserDetails userDetails) {
		return UserDetailsConfig.builder()
				.serviceId(userDetails.getServiceId())
				.usernameUri(userDetails.getUsernameUri())
				.mobileNumberUri(userDetails.getMobileNumberUri())
				.updateLoginInfoUri(userDetails.getUpdateLoginInfoUri()).build();
	}

	private VerifyCodeConfig getVerifyCodeConfig(AuthProperties.VerifyCode verifyCode) {
		return VerifyCodeConfig.builder()
				.expirationTime(verifyCode.getExpirationTime())
				.type(verifyCode.getType())
				.length(verifyCode.getLength())
				.value(verifyCode.getValue()).build();
	}
}
