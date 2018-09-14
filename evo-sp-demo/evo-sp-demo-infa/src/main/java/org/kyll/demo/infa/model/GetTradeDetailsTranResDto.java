package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 
 */
@Data
public class GetTradeDetailsTranResDto {
	private Long id;// 交易id 
	private Integer type;// 交易名称 1 购买 2收益3充值 4提现5 退款
	private String time;// 交易时间 
	private Double amount;// 交易金额 例如100.00
}
