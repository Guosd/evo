package org.kyll.demo.infa.model;

import lombok.Data;

import java.util.List;

/**
 * 交易明细接口响应对象
 */
@Data
public class GetTradeDetailsResDto {
	private Integer totalCount;// 总条数 
	private List<GetTradeDetailsTranResDto> tranList;//  
}
