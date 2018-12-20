package com.ritoinfo.framework.evo.sp.auth.authorization.entity;

import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseMapperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * User: Kyll
 * Date: 2018-12-20 11:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "auth_login", catalog = "evo_framework")
public class Login extends BaseMapperEntity<Long> {
	private Long id;
	private String username;
	private String password;
	private String email;
	private String mobileNumber;
}
