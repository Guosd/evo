package com.ritoinfo.framework.evo.sp.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
