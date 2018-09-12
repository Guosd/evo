package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 交易明细接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetTradeDetailsReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private Integer currentPage;// 页数 
	private Integer pageSize;// 条数 
	private Integer type;// 状态 0全部1 购买 2收益3充值 4提现5 退款
}
