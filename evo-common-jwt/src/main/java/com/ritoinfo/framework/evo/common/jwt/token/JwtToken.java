package com.ritoinfo.framework.evo.common.jwt.token;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.jwt.config.JwtConfig;
import com.ritoinfo.framework.evo.common.jwt.model.UserContext;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * User: Kyll
 * Date: 2017-12-07 14:20
 */
@Component
public class JwtToken {
	private static final String CLAIMS_USER_ID = "user_id";
	private static final String CLAIMS_USER_USERNAME = "user_username";
	private static final String CLAIMS_USER_NAME = "user_name";
	private static final String CLAIMS_USER_CODE = "user_code";

	private final JwtConfig jwtConfig;

	@Autowired
	public JwtToken(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	public String create(String id, String username, String name, String code) {
		Date now = DateUtil.now();
		return Jwts.builder()
				.setClaims(createClaims(id, username, name, code))
				.setIssuer(jwtConfig.getIssuer())
				.setIssuedAt(now)
				.setExpiration(DateUtil.plusMinutes(now, jwtConfig.getExpirationTime()))
				.signWith(SignatureAlgorithm.forName(jwtConfig.getAlgorithm()), jwtConfig.getSigningKey())
				.compact();
	}

	public String createRefresh(String id, String username, String name, String code) {
		Date now = DateUtil.now();
		return Jwts.builder()
				.setClaims(createClaims(id, username, name, code))
				.setIssuer(jwtConfig.getIssuer())
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(now)
				.setExpiration(DateUtil.plusMinutes(now, jwtConfig.getExpirationTime()))
				.signWith(SignatureAlgorithm.forName(jwtConfig.getAlgorithm()), jwtConfig.getSigningKey())
				.compact();
	}

	public UserContext parse(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSigningKey()).parseClaimsJws(token).getBody();

		return UserContext.builder()
				.id(claims.get(CLAIMS_USER_ID, String.class))
				.username(claims.getSubject())
				.name(claims.get(CLAIMS_USER_NAME, String.class))
				.code(claims.get(CLAIMS_USER_CODE, String.class)).jwtExpiration(claims.getExpiration())
				.build();
	}

	public String get(HttpServletRequest request) {
		String token = null;

		String header = request.getHeader(jwtConfig.getHeader());
		if (StringUtil.isNotBlank(header)) {
			token = header;
		}

		if (StringUtil.isBlank(token)) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (Const.JWT_TOKEN.equals(cookie.getName())) {
						token = cookie.getValue();
						break;
					}
				}
			}
		}

		return token;
	}

	public boolean verify(String token) {
		if (StringUtil.isBlank(token)) {
			return false;
		}

		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(jwtConfig.getSigningKey()).parseClaimsJws(token).getBody();
		} catch (Exception ignored) {
			return false;
		}

		return jwtConfig.getIssuer().equals(claims.getIssuer());
	}

	private Claims createClaims(String id, String username, String name, String code) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put(CLAIMS_USER_ID, id);
		claims.put(CLAIMS_USER_USERNAME, username);
		claims.put(CLAIMS_USER_NAME, name);
		claims.put(CLAIMS_USER_CODE, code);
		return claims;
	}
}
