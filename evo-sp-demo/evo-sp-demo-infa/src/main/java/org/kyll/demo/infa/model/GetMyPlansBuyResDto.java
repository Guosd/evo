package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 
 */
@Data
public class GetMyPlansBuyResDto {
	private String periodNo;// 项目期号 
	private String expiringDate;// 到期日 YYYY-MM-dd
	private Double investAmount;// 投资金额 
	private Double expectInterest;// 预期收益 
	private Long planId;// 产品id 
	private Integer status;// 状态 0收益中、1已结束、2投资中
	private Integer period;// 期限 月
}
