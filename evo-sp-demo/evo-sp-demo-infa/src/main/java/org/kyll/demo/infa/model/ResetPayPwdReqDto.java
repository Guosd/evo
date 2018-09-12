package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 忘记交易密码接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResetPayPwdReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private String idNumber;// 身份证号 
	private String newPayPassword;// 新密码 MD5
	private String mobile;// 手机号 
	private String status;// funCode 固定值：resetPayPwd
}
