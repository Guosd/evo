package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 修改登录密码接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ModifyLoginPwdReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private String oldPassword;// 老密码 MD5
	private String newPassword;// 新密码 MD5
	private String status;// funCode 固定值：modifyLoginPwd
}
