package org.kyll.demo.infa.model;

import lombok.Data;

import java.util.List;

/**
 * 大银行列表接口响应对象
 */
@Data
public class GetBanksResDto {
	private List<GetBanksBankResDto> bankList;//  
}
