package com.ritoinfo.framework.evo.sample.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-07-12 16:01
 */
@Data
public class CountryDto implements Serializable {
	private Long id;
	private String code;
	private String name;
	private Long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
}
