package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 忘记登录密码接口（已登陆 特殊老用户需特殊处理）请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResetLoginPwdReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private String mobile;// 手机号 
	private String newPassword;// 修改的密码 MD5
	private String status;// funCode 固定值：resetLoginPwd
}
