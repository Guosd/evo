package com.ritoinfo.framework.evo.sp.auth.assist;

import com.ritoinfo.framework.evo.common.model.UserContext;
import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.sp.auth.config.JwtConfig;
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

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * User: Kyll
 * Date: 2017-12-07 14:20
 */
@Slf4j
@Component
public class JwtAssist {
	private final JwtConfig jwt;

	@Autowired
	public JwtAssist(JwtConfig jwt) {
		this.jwt = jwt;
	}

	public String createAccess(UserContext userContext) {
		Date now = DateUtil.now();
		return Jwts.builder()
				.setClaims(createClaims(userContext))
				.setIssuer(jwt.getIssuer())
				.setIssuedAt(now)
				.setExpiration(DateUtil.plusMinutes(now, jwt.getExpirationTime()))
				.signWith(SignatureAlgorithm.forName(Const.JWT_ALGORITHM), jwt.getSigningKey())
				.compact();
	}

	public String createRefresh(UserContext userContext) {
		Date now = DateUtil.now();
		return Jwts.builder()
				.setClaims(createClaims(userContext))
				.setIssuer(jwt.getIssuer())
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(now)
				.setExpiration(DateUtil.plusMinutes(now, jwt.getRefreshExpirationTime()))
				.signWith(SignatureAlgorithm.forName(Const.JWT_ALGORITHM), jwt.getSigningKey())
				.compact();
	}

	public UserContext parse(String token) {
		return BeanUtil.mapToBean(parseToken(token), UserContext.class);
	}

	public Date extractExpiration(String accessToken) {
		return parseToken(accessToken).getExpiration();
	}

	public VerifyResult check(String token) {
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

		if (claims != null && jwt.getIssuer().equals(claims.getIssuer())) {
			verifyResult = VerifyResult.SUCCESS;
		}

		return verifyResult;
	}

	private Claims createClaims(UserContext userContext) {
		Claims claims = Jwts.claims().setSubject(userContext.getUsername());
		Map<String, Object> map = BeanUtil.beanToMap(userContext);
		map.forEach(claims::put);
		return claims;
	}

	private Claims parseToken(String token) {
		return Jwts.parser().setSigningKey(jwt.getSigningKey()).parseClaimsJws(token).getBody();
	}
}
