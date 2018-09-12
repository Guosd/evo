package org.kyll.demo.infa.model;

import lombok.Data;

/**
 * 
 */
@Data
public class GetBandBanksBankResDto {
	private String bankName;// 银行名称 
	private String account;// 银行卡帐号 
	private Long accountId;// 银行卡id 
}
