package org.kyll.demo.infa.model;

import lombok.Data;

import java.util.List;

/**
 * 开户行所在地一级接口响应对象
 */
@Data
public class GetProvincesResDto {
	private List<GetProvincesHotResDto> hotList;//  
	private List<GetProvincesProvinceResDto> provinceList;//  
}
