package com.ritoinfo.framework.evo.sp.auth.authorization.extend.token;

import com.ritoinfo.framework.evo.sp.auth.extend.LoginUser;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-12-29 15:30
 */
public class LoginUserAccessTokenConverter implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
		Object principal = oAuth2Authentication.getPrincipal();
		if (principal instanceof LoginUser) {
			LoginUser user = (LoginUser) principal;

			Map<String, Object> map = oAuth2AccessToken.getAdditionalInformation();
			map.put("id", user.getId());
			map.put("username", user.getUsername());
			map.put("email", user.getEmail());
			map.put("mobileNumber", user.getMobileNumber());
		}
		return oAuth2AccessToken;
	}
}
