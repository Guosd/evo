package com.ritoinfo.framework.evo.sys.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-08 11:43
 */
@Data
public class RoleDto implements Serializable {
	private Long id;
	private String name;
	private String code;
	private Long[] funcIds;
	private List<FuncDto> funcDtoList;
}
