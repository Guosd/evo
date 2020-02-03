package com.github.framework.evo.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-07-15 11:25
 */
public abstract class BaseXmlEntity<PK> extends BaseEntity {
	@Getter @Setter protected PK id;

	@Getter @Setter protected String deleteFlag;
	@Getter @Setter protected String createBy;
	@Getter @Setter protected Date createTime;
	@Getter @Setter protected String updateBy;
	@Getter @Setter protected Date updateTime;
}
