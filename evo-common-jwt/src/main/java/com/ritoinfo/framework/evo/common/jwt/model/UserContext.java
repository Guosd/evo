package com.ritoinfo.framework.evo.common.jwt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-03-09 15:15
 */
@Slf4j
@Builder
public class UserContext {
	@Getter private String id;
	@Getter private String username;
	@Getter private String name;
	@Getter private String code;
	@Getter private Date jwtExpiration;

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
