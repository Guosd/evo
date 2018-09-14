package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 购买产品接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InvestPlanReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private Double amount;// 金额 
	private Long planId;// 产品id 
	private String payPassword;// 交易密码 
}
