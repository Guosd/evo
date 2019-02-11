package com.ritoinfo.framework.evo.autoconfigure.auth;

import com.ritoinfo.framework.evo.sp.auth.config.AuthorizationConfig;
import com.ritoinfo.framework.evo.sp.auth.config.IamConfig;
import com.ritoinfo.framework.evo.sp.auth.config.JwtConfig;
import com.ritoinfo.framework.evo.sp.auth.config.PathConfig;
import com.ritoinfo.framework.evo.sp.auth.config.RbacConfig;
import com.ritoinfo.framework.evo.sp.auth.config.UserDetailsConfig;
import com.ritoinfo.framework.evo.sp.auth.config.VerifyCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@ConditionalOnClass(IamConfig.class)
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
	public IamConfig iamConfig() {
		return getIamConfig(authProperties.getIam());
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
		return getRbacConfig(authProperties.getIam().getRbac());
	}

	@Bean
	public UserDetailsConfig userDetailsConfig() {
		return getUserDetailsConfig(authProperties.getIam().getUserDetails());
	}

	@Bean
	public VerifyCodeConfig verifyCodeConfig() {
		return getVerifyCodeConfig(authProperties.getVerifyCode());
	}


	private AuthorizationConfig getAuthorizationConfig(AuthProperties.Authorization authorization) {
		return AuthorizationConfig.builder().serviceId(authorization.getServiceId()).build();
	}

	private IamConfig getIamConfig(AuthProperties.Iam iam) {
		return IamConfig.builder().serviceId(iam.getServiceId()).build();
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

	private RbacConfig getRbacConfig(AuthProperties.Iam.Rbac rbac) {
		return RbacConfig.builder().uri(rbac.getUri()).build();
	}

	private UserDetailsConfig getUserDetailsConfig(AuthProperties.Iam.UserDetails userDetails) {
		return UserDetailsConfig.builder()
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
