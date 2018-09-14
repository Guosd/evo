package org.kyll.demo.infa.model;

import lombok.Data;

import java.util.List;

/**
 * 获取投资列表接口响应对象
 */
@Data
public class GetMyPlansResDto {
	private Integer totalCount;// 总条数 
	private List<GetMyPlansBuyResDto> buylist;//  
}
