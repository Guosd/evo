package com.ritoinfo.framework.evo.sp.auth.infa.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-03-08 15:58
 */
@Data
public class UserDto implements Serializable {
	private Long id;
	private String username;
	private String password;
	private String name;
	private String code;
}
