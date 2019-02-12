package com.ritoinfo.framework.evo.auth.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2019-01-16 15:28
 */
@Data
public class UsernamePasswordParam {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
