package com.ritoinfo.framework.evo.auth.model;

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
	private String id;
	private String name;
	private String code;
	private String username;
	private String password;
	private String email;
	private String mobileNumber;
}
