package com.ritoinfo.framework.evo.autoconfigure.auth;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2019-02-09 11:13
 */
@Data
@ConfigurationProperties(prefix = "evo.auth")
public class AuthProperties {
	@Getter private Jwt jwt = new Jwt();
	@Getter private Path path = new Path();
	@Getter private VerifyCode verifyCode = new VerifyCode();
	@Getter private Authorization authorization = new Authorization();
	@Getter private UserDetails userDetails = new UserDetails();
	@Getter private Rbac rbac = new Rbac();

	@Data
	public static class Jwt {
		/**
		 * 发行人。默认http://evo.com
		 */
		private String issuer = "http://evo.com";
		/**
		 * 签名
		 */
		private String signingKey;
		/**
		 * 访问令牌过期时间，单位分钟。默认60 * 24 * 1
		 */
		private Integer expirationTime = 1440;
		/**
		 * 刷新令牌过期时间，单位分钟。默认 60 * 24 * 3
		 */
		private Integer refreshExpirationTime = 4320;
		/**
		 * 刷新令牌期间，旧令牌作为临时过渡令牌的过期时间，单位秒。默认 30
		 */
		private Integer oldExpirationTime = 30;
	}

	@Data
	public static class Path {
		/**
		 * 登录页面地址
		 */
		private String login;
		/**
		 * 排除访问控制URI数组
		 */
		private String[] excludes;
	}

	@Data
	public static class VerifyCode {
		/**
		 * 过期时间，单位分钟。默认2
		 */
		private Integer expirationTime = 2;
		/**
		 * random 随机; fix 固定。默认random
		 */
		private String type = "random";
		/**
		 * 当 type = random 时，验证码位数。默认6
		 */
		private Integer length = 6;
		/**
		 * 当 type= fix 时， 验证码值。默认123456
		 */
		private String value = "123456";
	}

	@Data
	public static class Authorization {
		/**
		 * 认证服务的spring.application.name。默认evo-sp-oauth2-authorization
		 */
		private String serviceId = "evo-sp-oauth2-authorization";
	}

	@Data
	public static class UserDetails {
		/**
		 * 提供用户信息服务的spring.application.name。默认evo-sp-sys
		 */
		private String serviceId = "evo-sp-sys";
		/**
		 * 通过用户登录名获取用户信息的 HTTP REST URI。默认/user/username
		 */
		private String usernameUri = "/user/username";
		/**
		 * 通过手机获取用户信息的 HTTP REST URI。默认/user/mobile-number
		 */
		private String mobileNumberUri = "/user/mobile-number";
		/**
		 * 更改登录用户信息的 HTTP REST URI。默认/user/login-info
		 */
		private String updateLoginInfoUri = "/user/login-info";
	}

	@Data
	public static class Rbac {
		/**
		 * 提供RBAC权限验证服务的spring.application.name。默认evo-sp-sys
		 */
		private String serviceId = "evo-sp-sys";
		/**
		 * 校验权限的 HTTP REST URI。默认/permission/check
		 */
		private String uri = "/permission/check";
	}
}
