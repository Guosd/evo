package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 开户行所在地一级接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetProvincesReqDto extends P2PReqDto {
}
