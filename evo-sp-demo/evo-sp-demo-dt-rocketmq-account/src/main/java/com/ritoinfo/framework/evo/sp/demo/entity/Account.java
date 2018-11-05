package com.ritoinfo.framework.evo.sp.demo.entity;

import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseMapperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * User: Kyll
 * Date: 2018-07-12 13:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "demo_account", catalog = "evo_demo_account")
public class Account extends BaseMapperEntity<Long> {
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private String name;
	private BigDecimal amount;
}
