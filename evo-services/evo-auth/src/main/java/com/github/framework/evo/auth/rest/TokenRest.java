package com.github.framework.evo.auth.rest;

import com.github.framework.evo.auth.bizz.TokenBizz;
import com.github.framework.evo.common.model.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2019-01-16 13:40
 */
@Slf4j
@RequestMapping("/token")
@RestController
public class TokenRest {
	@Autowired
	private TokenBizz tokenBizz;

	@PostMapping("/try")
	public String tryRefresh(@RequestBody String accessToken) {
		return tokenBizz.tryRefresh(accessToken);
	}

	@PostMapping("/refresh")
	public String refresh(@RequestBody String accessToken) {
		return tokenBizz.refresh(accessToken);
	}

	@PostMapping("/check")
	public UserContext check(@RequestBody String accessToken) {
		return tokenBizz.check(accessToken);
	}
}
