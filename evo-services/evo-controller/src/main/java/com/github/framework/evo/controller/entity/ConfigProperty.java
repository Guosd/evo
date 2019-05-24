package com.github.framework.evo.controller.entity;

import com.github.framework.evo.base.entity.BaseJpaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Kyll
 * Date: 2019-05-24 13:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "config_property", catalog = "evo_framework")
public class ConfigProperty extends BaseJpaEntity<Long> {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String label;
	private String application;
	private String profile;
	@Column(name = "key_")
	private String key;
	private String value;
	private String comment;
}
