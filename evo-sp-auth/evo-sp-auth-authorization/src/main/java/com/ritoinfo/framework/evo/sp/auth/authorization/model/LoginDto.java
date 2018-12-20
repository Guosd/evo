package com.ritoinfo.framework.evo.sp.auth.authorization.model;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-12-20 13:31
 */
@Data
public class LoginDto implements Serializable {
	private Long id;
	private String username;
	private String password;
	private String email;
	private String mobileNumber;
}
