package com.github.framework.evo.sys.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-05 23:06
 */
@Data
public class UserDto implements Serializable {
	private Long id;
	private String username;
	private String password;
	private String name;
	private String code;
	private String email;
	private String mobileNumber;
	private String freeze;
	private Date loginTime;
	private String loginIp;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Long[] roleIds;
	private List<RoleDto> roleDtoList;

}
