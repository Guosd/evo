package com.ritoinfo.framework.evo.common.password.crypto;

import com.ritoinfo.framework.evo.common.password.config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-03-09 16:54
 */
@Component
public class PasswordEncoder {
	//private final PasswordConfig passwordConfig;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public PasswordEncoder(PasswordConfig passwordConfig) {
		//this.passwordConfig = passwordConfig;
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder(passwordConfig.getSalt());
	}

	public String encode(String rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}
}
