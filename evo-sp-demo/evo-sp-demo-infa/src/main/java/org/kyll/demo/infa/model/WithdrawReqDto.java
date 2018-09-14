package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 提现接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WithdrawReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private Long accountId;// 银行卡id 
	private String account;// 银行卡号 
	private Double amount;// 提现金额 
	private String payPassword;// 交易密码 MD5
}
