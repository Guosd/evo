package com.ritoinfo.framework.evo.sp.sys.dto;

import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-03-05 23:06
 */
public class UserDto extends BaseDto<Long> {
	@Getter @Setter private String username;
	@Getter @Setter private String password;
	@Getter @Setter private String name;
	@Getter @Setter private String code;
	@Getter @Setter private String email;
	@Getter @Setter private String phone;
	@Getter @Setter private String freeze;
	@Getter @Setter private Date lastLoginTime;
	@Getter @Setter private String lastLoginIp;
}
