package org.kyll.demo.infa.model;

import lombok.Data;

import java.util.List;

/**
 * 开户行分行接口响应对象
 */
@Data
public class GetBranchBanksResDto {
	private List<GetBranchBanksBankResDto> bankList;//  
}
