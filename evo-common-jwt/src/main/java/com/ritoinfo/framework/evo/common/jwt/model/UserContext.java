package com.ritoinfo.framework.evo.common.jwt.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-03-09 15:15
 */
@Builder
public class UserContext {
	@Getter private String id;
	@Getter private String username;
	@Getter private String name;
	@Getter private String code;
	@Getter private Date jwtExpiration;
}
