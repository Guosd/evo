package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 大银行列表接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetBanksReqDto extends P2PReqDto {
}
