package com.ritoinfo.framework.evo.sp.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-07-12 16:01
 */
@Data
public class CountryDto implements Serializable {
	private Long id;
	private String countryCode;
	private String countryName;
}
