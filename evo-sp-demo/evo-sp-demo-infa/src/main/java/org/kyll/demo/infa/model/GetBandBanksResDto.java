package org.kyll.demo.infa.model;

import lombok.Data;

import java.util.List;

/**
 * 获取银行卡列表接口响应对象
 */
@Data
public class GetBandBanksResDto {
	private List<GetBandBanksBankResDto> bankList;//  
}
