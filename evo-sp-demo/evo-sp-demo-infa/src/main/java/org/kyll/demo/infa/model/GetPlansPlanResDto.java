package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 
 */
@Data
public class GetPlansPlanResDto {
	private Long planId;// 产品id 
	private String periodNo;// 项目期号 
	private Integer period;// 期限 
	private Double leftAmount;// 可投金额 
	private Double profitRate;// 年化率 %
	private Integer isEnd;// 是否满标 0否 1是
}
