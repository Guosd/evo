package com.ritoinfo.framework.evo.sp.sms.dto;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-04-20 16:20
 */
@Data
public class SmsDto {
	private String comCode;
	private String businessType;
	private String operatorCode;
	private String bizUser;
	private String bizNo;
	private String spNumber;
	private String phoneNo;
	private String content;
}
