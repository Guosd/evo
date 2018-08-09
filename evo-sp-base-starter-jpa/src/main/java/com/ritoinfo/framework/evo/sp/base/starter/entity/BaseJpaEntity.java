package com.ritoinfo.framework.evo.sp.base.starter.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-07-15 11:17
 */
public abstract class BaseJpaEntity<PK> extends BaseEntity {
	@Getter @Setter protected PK createBy;
	@Getter @Setter protected Date createTime;
	@Getter @Setter protected PK updateBy;
	@Getter @Setter protected Date updateTime;

	public abstract PK getId();

	public abstract void setId(PK id);
}