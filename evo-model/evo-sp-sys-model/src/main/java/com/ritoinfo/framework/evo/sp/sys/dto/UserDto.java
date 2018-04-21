package com.ritoinfo.framework.evo.sp.sys.dto;

import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-03-05 23:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseDto<Long> {
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
}
