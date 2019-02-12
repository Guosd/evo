package com.ritoinfo.framework.evo.sp.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-10-11 09:09
 */
@Data
public class StorageDto implements Serializable {
	private Long id;
	private String name;
	private Integer amount;
}
