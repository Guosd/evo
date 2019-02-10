package com.ritoinfo.framework.evo.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-03-09 15:15
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserContext implements Serializable {
	private String id;
	private String username;
	private String name;
	private String code;
	private String mobileNumber;
	private String accessToken;

	@SuppressWarnings("unchecked")
	public <P> P getId(Class clazz) {
		P pk;
		if (Long.class == clazz) {
			pk = (P) new Long(getId());
		} else if (Integer.class == clazz) {
			pk = (P) new Integer(getId());
		} else {
			log.error("不支持的主键类型: " + clazz);
			pk = null;
		}
		return pk;
	}
}
