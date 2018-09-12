package org.kyll.demo.infa.model;

import lombok.Data;

import java.util.List;

/**
 * 获取产品列表接口响应对象
 */
@Data
public class GetPlansResDto {
	private List<GetPlansPlanResDto> planList;//  
}
