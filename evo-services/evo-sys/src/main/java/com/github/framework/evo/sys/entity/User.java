package com.github.framework.evo.sys.entity;

import com.github.framework.evo.base.entity.BaseXmlEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-02-09 16:29
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseXmlEntity<Long> {
	private String username;// 用户名称
	private String password;// 密码
	private String name;// 姓名
	private String code;// 编码
	private String email;// Email地址
	private String mobileNumber;// 电话号码
	private String freeze;// 是否冻结 0 否， 1 是
	private Date loginTime;// 登录时间
	private String loginIp;// 登录IP
	private Date lastLoginTime;// 最后登录时间
	private String lastLoginIp;// 最后登录IP
}
