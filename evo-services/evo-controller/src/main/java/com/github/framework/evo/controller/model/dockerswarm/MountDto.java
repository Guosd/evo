package com.github.framework.evo.controller.model.dockerswarm;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2019-06-14 10:51
 */
@Data
public class MountDto {
	private String type;
	private String source;
	private String target;
}
