package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 充值校验验证码接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RechargeVerifyReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private String serialNo;// 流水号 
	private Integer code;// 验证码 
}
