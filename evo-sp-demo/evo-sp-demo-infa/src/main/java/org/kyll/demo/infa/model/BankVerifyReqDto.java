package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 添加银行卡发送验证码接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BankVerifyReqDto extends P2PReqDto {
	private String account;// 卡号 
	private String bankCode;// 所属银行 
	private String branchBankCode;// 开户行分行 
	private String mobile;// 银行卡预留手机号 
	private String realityName;// 姓名 
	private String idNumber;// 身份证号 
	private String seriNo;// 流水号 ？发送验证码接口，不传验证码；文档中目前没有实名验证接口
	private Long userId;// 用户Id 
}
