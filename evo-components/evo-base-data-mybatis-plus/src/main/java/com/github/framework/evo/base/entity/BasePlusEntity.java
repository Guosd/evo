package com.github.framework.evo.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2019-03-22 11:51
 */
public abstract class BasePlusEntity<PK> extends BaseEntity {
	@Getter @Setter protected PK createBy;
	@Getter @Setter protected Date createTime;
	@Getter @Setter protected PK updateBy;
	@Getter @Setter protected Date updateTime;

	public abstract PK getId();

	public abstract void setId(PK id);
}
