package com.ritoinfo.framework.evo.sp.sys.condition;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-02-13 13:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCondition extends BaseCondition<Long> {
	private String username;
	private String password;
	private String name;
	private String code;
	private String email;
	private String cellPhone;
	private String freeze;
	private String loginTime;
	private String loginIp;
	private String lastLoginTime;
	private String lastLoginIp;
}
