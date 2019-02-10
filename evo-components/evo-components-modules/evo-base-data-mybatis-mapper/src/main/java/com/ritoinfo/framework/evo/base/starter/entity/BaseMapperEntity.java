package com.ritoinfo.framework.evo.base.starter.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-07-15 11:17
 */
public abstract class BaseMapperEntity<PK> extends BaseEntity {
	@ColumnType(jdbcType = JdbcType.VARCHAR)
	@Getter @Setter protected PK createBy;
	@ColumnType(jdbcType = JdbcType.TIMESTAMP)
	@Getter @Setter protected Date createTime;
	@ColumnType(jdbcType = JdbcType.VARCHAR)
	@Getter @Setter protected PK updateBy;
	@ColumnType(jdbcType = JdbcType.TIMESTAMP)
	@Getter @Setter protected Date updateTime;

	public abstract PK getId();

	public abstract void setId(PK id);
}
