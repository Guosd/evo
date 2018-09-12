package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 我的资产信息接口接口响应对象
 */
@Data
public class GetAssetInfoResDto {
	private Double realReceiveInterest;// 已得收益 
	private Double expectInterest;// 预期收益 
	private Double balance;// 可用余额 
	private Double investAmount;// 已投金额 
	private Double totalAmount;// 总资产 ？
}
