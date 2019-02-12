package com.ritoinfo.framework.evo.base.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-11-16 17:58
 */
@MappedSuperclass
public abstract class BaseHibernateEntity<PK> extends BaseEntity {
	@Getter @Setter protected PK createBy;
	@Getter @Setter protected Date createTime;
	@Getter @Setter protected PK updateBy;
	@Getter @Setter protected Date updateTime;

	public abstract PK getId();

	public abstract void setId(PK id);
}
