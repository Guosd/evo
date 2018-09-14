package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 解绑银行卡接口（默认不可以解绑）请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnBandBankReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private Long accountId;// 银行卡id 
	private String account;// 银行卡号 
}
