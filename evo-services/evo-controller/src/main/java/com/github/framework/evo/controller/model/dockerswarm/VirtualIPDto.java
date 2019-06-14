package com.github.framework.evo.controller.model.dockerswarm;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2019-06-14 10:54
 */
@Data
public class VirtualIPDto {
	private String networkID;
	private String addr;
}
