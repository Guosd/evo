package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 设置交易接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SetPayPasswordReqDto extends P2PReqDto {
	private Long userId;// 用戶id 
	private String payPassword;// 交易密码 MD5
}
