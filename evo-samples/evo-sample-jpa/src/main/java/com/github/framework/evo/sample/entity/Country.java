package com.github.framework.evo.sample.entity;

import com.github.framework.evo.base.entity.BaseJpaEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Kyll
 * Date: 2018-07-12 13:09
 */
@Entity
@Table(name = "country", catalog = "evo_demo")
public class Country extends BaseJpaEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	@Getter @Setter private String code;
	@Getter @Setter private String name;
}
