package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 充值验证码接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetRechargeCodeReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private Long accountId;// 银行卡id 
	private String account;// 银行卡号 
	private String serialNo;// 流水号 
	private Double rechargeAmount;// 金额 
}
