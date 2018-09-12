package org.kyll.demo.infa.model;

import lombok.Data;

import java.util.List;

/**
 * 开户行所在地二级接口（市县）、搜索开户行所在地响应对象
 */
@Data
public class GetCityiesResDto {
	private List<GetCityiesCityResDto> cityList;//  
}
