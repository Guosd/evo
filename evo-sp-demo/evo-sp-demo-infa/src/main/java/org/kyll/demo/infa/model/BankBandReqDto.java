package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 校验绑卡验证码请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BankBandReqDto extends P2PReqDto {
	private Long userId;// 用户ID 
	private Integer code;// 验证码 
	private String serialNo;// 请求流水 
}
