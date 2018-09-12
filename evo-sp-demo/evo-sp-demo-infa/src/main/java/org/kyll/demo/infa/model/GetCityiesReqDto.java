package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 开户行所在地二级接口（市县）、搜索开户行所在地请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetCityiesReqDto extends P2PReqDto {
	private Integer provinceId;// 省份id 一级id
	private String cityName;// 市县名称 没有传空
}
