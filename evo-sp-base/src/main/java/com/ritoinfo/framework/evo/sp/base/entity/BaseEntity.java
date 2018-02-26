package com.ritoinfo.framework.evo.sp.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-02-09 16:25
 */
public abstract class BaseEntity<PK> implements Serializable {
	@Getter @Setter protected PK id;
	@Getter @Setter protected PK createBy;
	@Getter @Setter protected Date createTime;
	@Getter @Setter protected PK updateBy;
	@Getter @Setter protected Date updateTime;
}
