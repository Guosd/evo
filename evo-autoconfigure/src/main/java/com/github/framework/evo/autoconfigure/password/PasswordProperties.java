package com.github.framework.evo.autoconfigure.password;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2019-02-09 11:13
 */
@Data
@ConfigurationProperties(prefix = "evo.password")
public class PasswordProperties {
	/**
	 * BCrypt强哈希函数强度。强度参数越大，散列密码所需的工作(以指数形式)就越多。默认值是10。
	 */
	private int strength = 10;
}
