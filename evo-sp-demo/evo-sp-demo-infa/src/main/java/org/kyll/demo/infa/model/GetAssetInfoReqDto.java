package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 我的资产信息接口接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetAssetInfoReqDto extends P2PReqDto {
	private Long userId;// 用户id 
}
