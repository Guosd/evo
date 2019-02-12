package com.github.framework.evo.sample.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: Kyll
 * Date: 2018-10-11 09:09
 */
@Data
public class AccountDto implements Serializable {
	private Long id;
	private String name;
	private BigDecimal amount;
}
