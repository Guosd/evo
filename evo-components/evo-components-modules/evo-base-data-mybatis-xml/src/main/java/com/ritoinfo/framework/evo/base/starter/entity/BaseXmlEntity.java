package com.ritoinfo.framework.evo.base.starter.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-07-15 11:25
 */
public abstract class BaseXmlEntity<PK> extends BaseEntity {
	@Getter @Setter protected PK id;
	@Getter @Setter protected PK createBy;
	@Getter @Setter protected Date createTime;
	@Getter @Setter protected PK updateBy;
	@Getter @Setter protected Date updateTime;
}
