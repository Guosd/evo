package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 绑定手机号接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SetMobileReqDto extends P2PReqDto {
	private Long userId;// 用户id 
	private String mobile;// 手机号 
	private String status;// funCode 固定值：setMobile
}
