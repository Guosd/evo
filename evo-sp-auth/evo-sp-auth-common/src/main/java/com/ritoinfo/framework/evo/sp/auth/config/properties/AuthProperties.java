package com.ritoinfo.framework.evo.sp.auth.config.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-06-06 09:57
 */
@Component
@ConfigurationProperties(prefix = "evo.sp.auth")
public class AuthProperties {
	@Getter private Jwt jwt = new Jwt();
	@Getter private Path path = new Path();
	@Getter private VerifyCode verifyCode = new VerifyCode();
	@Getter private Authorization authorization = new Authorization();
	@Getter private UserDetails userDetails = new UserDetails();
	@Getter private Rbac rbac = new Rbac();

	@Data
	public static class Jwt {
		private String issuer = "http://evo.com";// 发行人
		private String signingKey;// 签名
		private Integer expirationTime = 1440;// 过期时间 单位分钟 默认60 * 24 * 1
		private Integer refreshExpirationTime = 4320;// 刷新过期时间 默认 60 * 24 * 3
	}

	@Data
	public static class Path {
		private String login;
		private String[] excludes;
	}

	@Data
	public static class VerifyCode {
		private Integer expirationTime = 2;// 过期时间 单位分钟 默认 2
		private String type = "random";// random 随机; fix 固定
		private Integer length = 6;// 当 type = random 时，验证码位数
		private String value = "123456";// 当 type= fix 时， 验证码值
	}

	@Data
	public static class Authorization {
		private String serviceId = "evo-sp-oauth2-authorization";// 认证服务的spring.application.name
	}

	@Data
	public static class UserDetails {
		private String serviceId = "evo-sp-sys";// 提供用户信息服务的spring.application.name
		private String usernameUri = "/user/username";// 通过用户登录名获取用户信息的 HTTP REST URI
		private String mobileNumberUri = "/user/mobile-number";// 通过手机获取用户信息的 HTTP REST URI
		private String updateLoginInfoUri = "/user/login-info";// 更改登录用户信息的 HTTP REST URI
	}

	@Data
	public static class Rbac {
		private String serviceId = "evo-sp-sys";// 提供RBAC权限验证服务的spring.application.name
		private String uri = "/permission/check";// 校验权限的 HTTP REST URI
	}
}
