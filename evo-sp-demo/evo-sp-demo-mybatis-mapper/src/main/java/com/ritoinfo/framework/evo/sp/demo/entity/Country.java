package com.ritoinfo.framework.evo.sp.demo.entity;

import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseMapperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Kyll
 * Date: 2018-07-12 13:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "country", catalog = "evo_demo")
public class Country extends BaseMapperEntity<Long> {
	@Id
	/*@KeySql(sql = "select SEQ_COUNTRY.nextval from dual", order = ORDER.BEFORE)*/
	private Long id;
	private String countryCode;
	private String countryName;
}
