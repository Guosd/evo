package com.ritoinfo.framework.evo.common.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-03-09 15:15
 */
@Slf4j
@AllArgsConstructor
@Data
public class UserContext implements Serializable {
	private String id;
	private String username;
	private String name;
	private String code;
	private String mobileNumber;
	private Date jwtExpiration;

	public UserContext() {
	}

	@SuppressWarnings("unchecked")
	public <PK> PK getId(Class clazz) {
		PK pk;
		if (Long.class == clazz) {
			pk = (PK) new Long(getId());
		} else if (Integer.class == clazz) {
			pk = (PK) new Integer(getId());
		} else {
			log.warn("不支持的主键类型: " + clazz);
			pk = null;
		}
		return pk;
	}
}
