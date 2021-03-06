package com.github.framework.evo.activiti.dto;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-04-10 11:39
 */
@Data
public class WithdrawDto {
	private String username;
	private String processInstanceId;
	private String historicTaskInstanceId;
	private String comment;
}
