package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 获取产品详情接口响应对象
 */
@Data
public class GetPlanDetailResDto {
	private Double amount;// 融资金额 
	private Double leftAmount;// 可投金额 
	private Double profitRate;// 年化率 
	private Integer period;// 出借期限 
	private String repaymentType;// 还款方式 
	private Double expectInterest;// 万元预估收益 
	private String beginTime;// 开始时间 
	private String endTime;// 结束时间 
	private Integer idEnd;// 是否满标 0否 1是
	private String periodNo;// 期号 
}
