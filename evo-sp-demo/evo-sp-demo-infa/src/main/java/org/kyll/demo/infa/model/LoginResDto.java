package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 帐号密码登录接口响应对象
 */
@Data
public class LoginResDto {
	private Long userId;// 用户id 
	private String mobile;// 手机号 
}
