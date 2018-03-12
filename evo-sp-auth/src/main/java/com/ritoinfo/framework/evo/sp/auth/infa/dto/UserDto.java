package com.ritoinfo.framework.evo.sp.auth.infa.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-08 15:58
 */
public class UserDto {
	@Getter @Setter private Long id;
	@Getter @Setter private String username;
	@Getter @Setter private String password;
	@Getter @Setter private String name;
	@Getter @Setter private String code;
}
