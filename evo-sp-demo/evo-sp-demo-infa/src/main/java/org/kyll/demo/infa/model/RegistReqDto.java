package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 注册接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RegistReqDto extends P2PReqDto {
	private String name;// 帐号 
	private String password;// 密码 
	private String mobile;// 手机号 
}
