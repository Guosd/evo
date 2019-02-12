package com.github.framework.evo.sms.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-01-29 15:19
 */
@Data
@Component
@ConfigurationProperties(prefix = "evo.sp.sms")
public class SmsProperties {
	private boolean enabled;
	private String address;
	private String comCode;
	private String orgCode;
	private String businessNo;
	private String businessTypeCode;
	private String channel;
	private String ip;
}
