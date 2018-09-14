package org.kyll.demo.infa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kyll.demo.infa.model.base.P2PReqDto;

/**
 * 开户行分行接口请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetBranchBanksReqDto extends P2PReqDto {
	private String cityCode;// 二级城市id 
	private String bankCode;// 银行id 
	private String branchBankName;// 分行名称 没有传空
}
