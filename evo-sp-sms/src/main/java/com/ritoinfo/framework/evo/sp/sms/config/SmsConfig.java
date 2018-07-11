package com.ritoinfo.framework.evo.sp.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-06-05 18:09
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {
	private String address;
	private String comCode;
	private String orgCode;
	private String businessNo;
	private String businessTypeCode;
	private String channel;
	private String ip;
}
