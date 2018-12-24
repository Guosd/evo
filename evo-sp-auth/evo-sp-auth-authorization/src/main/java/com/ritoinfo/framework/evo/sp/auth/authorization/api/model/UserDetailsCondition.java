package com.ritoinfo.framework.evo.sp.auth.authorization.api.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-12-12 14:42
 */
@Builder
@Data
public class UserDetailsCondition implements Serializable {
	private String username;
	private String password;
	private String mobileNumber;
}
