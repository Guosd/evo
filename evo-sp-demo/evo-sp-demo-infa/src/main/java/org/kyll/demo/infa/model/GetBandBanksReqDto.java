package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 获取银行卡列表接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetBandBanksReqDto extends P2PReqDto {
	private Long userId;// 用户id 
}
