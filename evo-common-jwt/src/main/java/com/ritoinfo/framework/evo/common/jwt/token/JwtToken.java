package com.ritoinfo.framework.evo.common.jwt.token;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.jwt.config.JwtConfig;
import com.ritoinfo.framework.evo.common.jwt.model.UserContext;
import com.ritoinfo.framework.evo.common.jwt.model.VerifyResult;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
				.setExpiration(DateUtil.plusMinutes(now, jwtConfig.getRefreshExpirationTime()))
				.signWith(SignatureAlgorithm.forName(jwtConfig.getAlgorithm()), jwtConfig.getSigningKey())
				.compact();
	}

	public UserContext parse(String token) {
		Claims claims = parseToken(token);

		return UserContext.builder()
				.id(claims.get(CLAIMS_USER_ID, String.class))
				.username(claims.getSubject())
				.name(claims.get(CLAIMS_USER_NAME, String.class))
				.code(claims.get(CLAIMS_USER_CODE, String.class)).jwtExpiration(claims.getExpiration())
				.build();
	}

	public String getToken(HttpServletRequest request) {
		String token = null;

		String header = request.getHeader(Const.JWT_TOKEN_HEADER);
		if (StringUtil.isNotBlank(header)) {
			token = header;
		}

		if (StringUtil.isBlank(token)) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(Const.JWT_TOKEN_HEADER)) {
						token = cookie.getValue();
						break;
					}
				}
			}
		}

		return token;
	}

	public VerifyResult verify(String token) {
		VerifyResult verifyResult = VerifyResult.FAILURE;

		Claims claims = null;
		try {
			claims = parseToken(token);
		} catch (ExpiredJwtException e) {
			verifyResult = VerifyResult.EXPIRED;
		} catch (UnsupportedJwtException e) {
			verifyResult = VerifyResult.UNSUPPORTED;
		} catch (MalformedJwtException e) {
			verifyResult = VerifyResult.MALFORMED;
		} catch (SignatureException e) {
			verifyResult = VerifyResult.SIGNATURE;
		} catch (IllegalArgumentException e) {
			verifyResult = VerifyResult.ILLEGAL_ARGUMENT;
		}

		if (claims != null && jwtConfig.getIssuer().equals(claims.getIssuer())) {
			verifyResult = VerifyResult.SUCCESS;
		}

		return verifyResult;
	}

	private Claims createClaims(String id, String username, String name, String code) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put(CLAIMS_USER_ID, id);
		claims.put(CLAIMS_USER_USERNAME, username);
		claims.put(CLAIMS_USER_NAME, name);
		claims.put(CLAIMS_USER_CODE, code);
		return claims;
	}

	private Claims parseToken(String token) {
		return Jwts.parser().setSigningKey(jwtConfig.getSigningKey()).parseClaimsJws(token).getBody();
	}
}
