package com.ritoinfo.framework.evo.sp.auth.authorization.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-12-12 14:42
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDetailsDto implements Serializable {
	private String username;
	private String password;
	private String mobileNumber;
}
